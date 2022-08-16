package kz.timka.mvc.controllers;

import kz.timka.mvc.dao.PersonDAO;
import kz.timka.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Person> all = personDAO.findAll();
        model.addAttribute("people", all);
        return "people/index";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Person person = personDAO.findById(id);
        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String htmlFormForCreateNewPerson(Model model) {
        // для формы thymeleaf надо указывать кого создаем, поэтому передаем пустой обьект person
        model.addAttribute("newPerson", new Person());
        return "people/new";
    }

    @PostMapping // ошибки валидации помещается в BindingResult, он всегда должен в аргументе идти после той модели которой валидидруется
    public String create(@ModelAttribute("newPerson") @Valid Person person, BindingResult bindingResult) {
        // ModelAttribute создает за нас обьект person кладет через сеттеры поля из пришедшие из формы и также
        // сразу кладет его в модел и на странице мы можем к нему обратиться
        if(bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        return "people/successPage";
    }

    @ModelAttribute("headerMessage") // в каждом методе текущего контроллера в модели будет ключ headerMessage со значение
    // Welcome to our website. Использовать когда нужно во всех моделях этого контроллера. Любая модель из этого контроллера
    // по умолчанию будет иметь значение с ключом headerMessage. // можно также добавлять и обьекты
    public String myHeaderMessage() {
       return "Welcome to our website";
    }

//    @ModelAttribute("commonPerson")
//    public Person myCommonPerson() {
//        Person commonPerson = new Person();
//        commonPerson.setName("Common person");
//        return commonPerson;
//    }

    @GetMapping("/{id}/edit")
    public String formForEdit(@PathVariable Long id, Model model) {
        Person person = personDAO.findById(id);
        model.addAttribute("editPerson", person);
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute("editPerson") @Valid Person person, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
