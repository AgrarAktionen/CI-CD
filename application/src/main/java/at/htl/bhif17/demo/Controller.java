package at.htl.bhif17.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Random;

@Dependent
@Transactional
public class Controller {
    public Button button;
    Random random;
    @Inject PersonDao dao;

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
        var persons = dao.getAll();
        persons.stream().forEach(System.out::println);
    }
    public void click(ActionEvent __) {
        var matNr = String.format("%05d", random.nextInt(99999));
        var person = Person.builder().firstName("Engelbert").lastName("Breitfuß").matNr(matNr).build();
        dao.save(person);
        System.out.println("------");
        load();
    }
}
