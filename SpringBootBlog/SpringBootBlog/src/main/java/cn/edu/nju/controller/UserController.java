package cn.edu.nju.controller;

import cn.edu.nju.domain.User;
import cn.edu.nju.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Thpffcj on 2017/10/22.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 从 用户存储库 获取用户列表
     * @return
     */
    private List<User> getUserlist() {
        return userRepository.listUser();
    }

    /**
     * 查询所用用户
     * @return
     */
    @GetMapping
    public ModelAndView list(Model model) {
        model.addAttribute("userList", getUserlist());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 根据id查询用户
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        User user = userRepository.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 获取 form 表单页面
     * @return
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    /**
     * 新建用户
     * @return
     */
    @PostMapping
    public ModelAndView create(User user) {
        user = userRepository.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/users");
    }

    /**
     * 删除用户
     * @return
     */
    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Model model) {
        userRepository.deleteUser(id);

        model.addAttribute("userList", getUserlist());
        model.addAttribute("title", "删除用户");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 修改用户
     * @return
     */
    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/form", "userModel", model);
    }
}