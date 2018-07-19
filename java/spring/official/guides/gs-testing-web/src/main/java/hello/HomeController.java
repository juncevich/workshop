package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Aleksandr Juncevich
 * on 03 Jan 2017.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public
    @ResponseBody
    String greeting() {
        return "Hello World";
    }
}
