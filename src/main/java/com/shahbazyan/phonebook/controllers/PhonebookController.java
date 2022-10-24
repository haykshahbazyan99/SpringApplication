package com.shahbazyan.phonebook.controllers;

import com.shahbazyan.phonebook.models.Contacts;
import com.shahbazyan.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PhonebookController {

    @Autowired
    private ContactRepository contactRepository;


    @GetMapping("/contacts")
    public String contacts(Model model) {
        Iterable<Contacts> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "all-contacts";
    }

    @GetMapping("/contacts/add")
    public String addContact(Model model) {
        return "add-contact";
    }

    @PostMapping("/contacts/add")
    public String contactAdd(@RequestParam String full_name, @RequestParam String contact, @RequestParam String description, Model model){
        Contacts newContact = new Contacts(full_name, contact, description);
        contactRepository.save(newContact);
        return "redirect:/contacts";
    }

    @GetMapping("/contacts/{id}")
    public String contactDetails(@PathVariable(value = "id") long id, Model model){

        if(!contactRepository.existsById(id)) {
            return "redirect:/contacts";
        }

        Optional<Contacts> post = contactRepository.findById(id);
        ArrayList<Contacts> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("contact", result);
        return "contact-details";
    }

    @GetMapping("/contacts/{id}/edit")
    public String contactEdit(@PathVariable(value = "id") long id, Model model){

        if(!contactRepository.existsById(id)) {
            return "redirect:/contacts";
        }
        Optional<Contacts> contact = contactRepository.findById(id);
        ArrayList<Contacts> result = new ArrayList<>();
        contact.ifPresent(result::add);
        model.addAttribute("contact", result);
        return "contact-edit";
    }


    @PostMapping("/contacts/{id}/edit")
    public String contactUpdate(@PathVariable(value = "id") long id, @RequestParam String full_name, @RequestParam String contact, @RequestParam  String description, Model model){

        Contacts contactById = contactRepository.findById(id).orElseThrow();
        contactById.setFull_name(full_name);
        contactById.setContact(contact);
        contactById.setDescription(description);
        contactRepository.save(contactById);

        return "redirect:/contacts";
    }


    @PostMapping("/contacts/{id}/remove")
    public String contactDelete(@PathVariable(value = "id") long id){
        Contacts contact = contactRepository.findById(id).orElseThrow();
        contactRepository.delete(contact);
        return "redirect:/contacts";
    }

}
