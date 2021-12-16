package com.aktionen.agrar.dao;

import ai.djl.ModelException;
import ai.djl.engine.Engine;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.transform.Normalize;
import ai.djl.modality.cv.transform.Resize;
import ai.djl.modality.cv.translator.ImageClassificationTranslator;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import com.aktionen.agrar.model.*;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;

import javax.enterprise.context.Dependent;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@Named
@Dependent
public class PredectionDao {
    @Inject
    EntityManager em;

    @Inject
    ItemDao itemDao;

    public void insertAll(PredictedItem predictedItem, Item item) throws TranslateException, IOException, ModelException, ImageReadException {

        final float[] MEAN = {103.939f, 116.779f, 123.68f};
        final float[] STD = {1f, 1f, 1f};

        try{
        if(item.getItemId() == 0){
            System.err.println("Could not find item for prediction!");
        }else {
            /*Classification Algorithm*/
            if (!item.getBildLink().isEmpty()) { //check if BildLink is empty

                URL url = new URL(item.getBildLink());
                //___________________________________To JPEG__________________________________________//

                BufferedImage images = ImageIO.read(url);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(images, "jpeg", os);
                InputStream inputStream = new ByteArrayInputStream(os.toByteArray());

                //_____________________________________________________________________________//
                Image image;
                try (InputStream is = inputStream) {
                    image = ImageFactory.getInstance().fromImage(Imaging.getBufferedImage(is));
                }

                if (image != null) {
                    Criteria<Image, Classifications> criteria;
                    if ("TensorFlow".equals(Engine.getInstance().getEngineName())) {
                        Translator<Image, Classifications> translator = ImageClassificationTranslator.builder()
                                .addTransform(new Resize(224))
                                .addTransform(new Normalize(MEAN, STD))
                                .build();
                        criteria =
                                Criteria.builder()
                                        .setTypes(Image.class, Classifications.class)
                                        .optArtifactId("resnet")
                                        .optTranslator(translator)
                                        .optProgress(new ProgressBar())
                                        .build();
                    } else {
                        criteria =
                                /*
                                Criteria.builder()
                                .setTypes(Image.class, Classifications.class) // defines input and output data type
                                .optTranslator(ImageClassificationTranslator.builder().optSynsetArtifactName("synset.txt").build())
                                .optModelUrls("https://resources.djl.ai/benchmark/squeezenet_v1.1.tar.gz") // search models in specified path
                                .build();


                                 */


                                Criteria.builder()

                                        .setTypes(Image.class, Classifications.class)
                                        .optArtifactId("resnet")
                                        .optProgress(new ProgressBar())
                                        .build();


                    }

                    try (ZooModel<Image, Classifications> model = ModelZoo.loadModel(criteria);
                        /*ZooModel<Image, Classifications> model = criteria.loadModel();*/
                         Predictor<Image, Classifications> predictor = model.newPredictor()) {

                        Classifications result = predictor.predict(image);
                        /*Classification Algorithm*/
                        System.out.println(result);

                        predictedItem.setPredictedItemPk(new PredictedItemPk(item.getItemId(), String.valueOf(result.best())));
                        predictedItem.setClassname(String.valueOf(result.best().getClassName()));
                        predictedItem.setProbability(result.best().getProbability());
                        //em.persist(item);
                        em.merge(predictedItem);
                        em.flush();
                    }
                } else {

                    predictedItem.setPredictedItemPk(new PredictedItemPk(item.getItemId(), "empty"));
                    predictedItem.setClassname("empty");
                    predictedItem.setProbability(0.0);
                    //em.persist(item);
                    em.merge(predictedItem);
                    em.flush();
                }

            } else {
                predictedItem.setPredictedItemPk(new PredictedItemPk(item.getItemId(), "empty"));
                predictedItem.setClassname("empty");
                predictedItem.setProbability(0.0);
            }
        }
        }catch (Exception e){
            System.err.println("Failed to predict item: " + e);
        }
    }
    public List<PredictedItem> getAll() {
        return em.createQuery("select p from PredictedItem p ", PredictedItem.class).getResultList();
    }

}
