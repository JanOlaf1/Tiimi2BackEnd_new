package k24.op1.dogbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import k24.op1.dogbackend.domain.Product;
import k24.op1.dogbackend.domain.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/dogstore")
    public String showDogStore(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "dogstore";
    }
    @GetMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        if (id != null) {
            productRepository.deleteById(id);
        }
        return "redirect:/dogstore";
    }
    @GetMapping("/editproduct/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        if (id != null) {
            Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));
            model.addAttribute("product", product);
        }
        return "redirect:/dogstore";
    }
    
}
