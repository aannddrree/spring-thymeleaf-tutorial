package com.matera.blog.controller;

import com.matera.blog.model.TypePost;
import com.matera.blog.repository.TypePostRepository;
import com.matera.blog.service.TypePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.matera.blog.model.Post;
import com.matera.blog.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller //Define a classe como um bean do Spring
public class PostController {
	
	@Autowired
	private PostService service; //	Injeta a classe de serviços

	@Autowired
	private TypePostService typePostService;
	
	//Vai para tela principal do CRUD aonde são listados todos os posts
	@GetMapping("/")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/post");
		mv.addObject("posts", service.findAll());
		
		return mv;
	}
	
	//Vai para tela de adição de post
	@GetMapping("/add")
	public ModelAndView add(Post post) {
		
		ModelAndView mv = new ModelAndView("/postAdd");
		mv.addObject("post", post);
		mv.addObject("tipos", typePostService.findAll());

		return mv;
	}
	
	//Vai para tela de edição de posts (mesma tela de adição, contudo é enviado para a view um objeto que já existe)
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id).get());
	}
	
	//Exclui um post por seu ID e redireciona para a tela principal
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}
	
	//Recebe um objeto preenchido do Thymeleaf e valida 
	//Se tudo estiver ok, salva e volta para tela principal
	//Se houver erro, retorna para tela atual exibindo as mensagens de erro
	@PostMapping("/save")
	public ModelAndView save(Post post, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(post);
		}
		
		service.save(post);
		
		return findAll();
	}
	
}
