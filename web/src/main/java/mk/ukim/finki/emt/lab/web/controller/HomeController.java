//package mk.ukim.finki.emt.lab.web.controller;
//
//
//import mk.ukim.finki.emt.lab.model.Book;
//import mk.ukim.finki.emt.lab.service.BookService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(value={"/", "/home"})
//public class HomeController {
//
//    private final BookService bookService;
//
//    public HomeController(BookService bookService) {
//        this.bookService = bookService;
//    }
//    @GetMapping
//    public String getBookPage(@RequestParam(required = false) String error, Model model) {
//        if (error != null && !error.isEmpty()) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//        }
//        List<Book> books = this.bookService.findAll();
//        model.addAttribute("books", books);
//        model.addAttribute("bodyContent", "books");
//        return "master-template";
//    }
//
//    @GetMapping("/access_denied")
//    public String getAccessDeniedPage(Model model) {
//        model.addAttribute("bodyContent", "access_denied");
//        return "master-template";
//    }
//}
