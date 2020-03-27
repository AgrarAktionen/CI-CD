package at.htl.bhif17.demo;

import at.htl.bhif17.demo.dao.PersonDao;
import at.htl.bhif17.demo.dao.SchoolDao;
import at.htl.bhif17.demo.model.Person;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Random;

@Dependent
@Transactional
public class Controller {
    public Button button;
    Random random;
    @Inject PersonDao personDao;
    @Inject SchoolDao schoolDao;

    public void initialize() {
        System.out.println("initialize()");
        random = new Random();
        load();
    }
    @PostConstruct
    void init() {
        System.out.println("init()");
    }
    void load() {
        var school = schoolDao.findById(1);
        System.out.println("school = " + school + " persons:" + school.getPersons().size());
        school.getPersons().forEach(System.out::println);
    }
    public void click(ActionEvent __) {
        var matNr = String.format("%05d", random.nextInt(99999));
        var person = Person.builder().firstName("Engelbert").lastName("Breitfuß").matNr(matNr).build();
        personDao.save(person);
        System.out.println("------");
        load();
    }
}
