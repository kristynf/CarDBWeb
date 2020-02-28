package com.kristyn.cardbweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }
    @GetMapping("/addcategory")
    public String newCategory(Model model) {

        model.addAttribute("category", new Category());

        return "categoryform";
    }

    @PostMapping("/processcategory")
    public String processCompany(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/addcar";
    }

    @GetMapping("/addcar")
    public String newCar(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/processcar")
    public String processCar(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        model.addAttribute("categorySearch", categoryRepository.findByNameIgnoreCase(search));
        model.addAttribute("carSearch1", carRepository.findCarByMakeIgnoreCase(search));
        model.addAttribute("carSearch2", carRepository.findCarByModelIgnoreCase(search));
        model.addAttribute("carSearch3", carRepository.findCarByYearIgnoreCase(search));
        return "list";
    }

    @RequestMapping("detail/{id}")
    public String showCategory(@PathVariable("id") long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "showcategory";
    }

    @RequestMapping("detailc/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "showcar";
    }

    @RequestMapping("update/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "categoryform";
    }

    @RequestMapping("updatec/{id}")
    public String updateCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("delete/{id}")
    public String deleteCategory(@PathVariable("id") long id, Model model) {
        categoryRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("deletec/{id}")
    public String deleteCar(@PathVariable("id") long id, Model model) {
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/listcars")
    public String listCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "showcars";
    }
    @RequestMapping("/category/{id}")
    public String displayCat(@PathVariable("id") long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id).get());
        model.addAttribute("cars", carRepository.findAll());
        return "listcategory";
    }
}
