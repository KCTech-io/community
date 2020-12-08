package io.community.translation.munselvom.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.community.translation.munselvom.article.Articles;
import io.community.translation.munselvom.article.ArticlesService;
import io.community.translation.munselvom.auth.LoginFormData;
import io.community.translation.munselvom.auth.UserForm;
import io.community.translation.munselvom.auth.UserList;
import io.community.translation.munselvom.auth.UserService;
import io.community.translation.munselvom.validator.UserValidator;

@Controller

public class HomeController {
	
	@Autowired
	ArticlesService articleService;
	
	@Autowired
	UserService userService;
	 
	@Autowired
	private UserValidator userValidator;
	 
	// Binding of Form
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
	      // Form target
	      Object target = dataBinder.getTarget();
	      if (target == null) {
	         return;
	      }
	 
	      if (target.getClass() == UserForm.class) {
	         dataBinder.setValidator(userValidator);
	      }
	   }

	
	@GetMapping("/")
	
   public String home(Model model) {
		
		
		userService.updateSession();
		UserList userList = userService.getAuthStatus();
				
		model.addAttribute("loginFormData", new LoginFormData());

		if (!Objects.isNull(userList)) {
			model.addAttribute("userdesc", userList.getUserdesc());
			model.addAttribute("useremail", userList.getEmailid());
		}
		else {
			model.addAttribute("userdesc", "");
			model.addAttribute("useremail", "");
		}
		
		model.addAttribute("loginerrormess", "Success");
		
		return "homepage/index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("loginFormData") LoginFormData loginForm, //
			Model model) throws Exception
 {
			System.out.println("User Id "+loginForm.getUsername());
						
			String login_mess = userService.userLogin(loginForm.getUsername(),loginForm.getUserpass());
			
			System.out.println("login Status "+ login_mess+" End of message");
			
			//return login_mess;
			
			
			
			if (login_mess != "Success") {
				model.addAttribute("loginerrormess", login_mess);
				model.addAttribute("userdesc", "");
				model.addAttribute("useremail","");
				
				//throw new Exception("Authentication Failed");
			} else {
			
				model.addAttribute("loginerrormess", "Success");
				model.addAttribute("userdesc", userService.getUserByName(loginForm.getUsername()).getUserdesc() );
				model.addAttribute("useremail", userService.getUserByName(loginForm.getUsername()).getEmailid() );
			}
			//return "redirect:/";
			
			return "homepage/index";
	}

	@RequestMapping(value = "/logoutuser", method = RequestMethod.GET)
	public String logoutUser(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		userService.userLogout(request,response);
		//return "redirect:/login?logout";
		return "redirect:/";
	}


	@GetMapping("/welcome")
	
	   public String welcome(Model model) {
			return "trans-test";
	}
	
	@GetMapping("/tamil")
	
	   public String tamil(Model model) {
			return "/homepage/translate-test";
	}
	
	@GetMapping("/template")
	
	   public String template(Model model) {
			return "homepage/template";
		}
    
	@GetMapping("/login")
    public String signInVolunteer(Model model) {

        // TODO: save project in DB here

        return "homepage/login";
    }
	
	@GetMapping("/menu")
    public String loadMenu(Model model) {

        // TODO: save project in DB here

        return "menu";
    }
	@GetMapping("/common")
    public String loadCommon(Model model) {

        // TODO: save project in DB here

        return "common";
    }
	@GetMapping("/articles")
    public String loadarticles(Model model) {

		userService.updateSession();
		UserList userList = userService.getAuthStatus();
		

		model.addAttribute("loginFormData", new LoginFormData());

		if (!Objects.isNull(userList)) {
			model.addAttribute("userdesc", userList.getUserdesc());
			model.addAttribute("useremail", userList.getEmailid());
		}
		else {
			model.addAttribute("userdesc", "");
			model.addAttribute("useremail", "");
		}
		
		model.addAttribute("loginerrormess", "Success");

        return "/homepage/articles";
    }
	@GetMapping("/translate")
    public String loadtranslate(Model model) {
				
		userService.updateSession();
		UserList userList = userService.getAuthStatus();
		

		model.addAttribute("loginFormData", new LoginFormData());

		if (!Objects.isNull(userList)) {
			model.addAttribute("userdesc", userList.getUserdesc());
			model.addAttribute("useremail", userList.getEmailid());
		}
		else {
			model.addAttribute("userdesc", "");
			model.addAttribute("useremail", "");
		}
		
		model.addAttribute("loginerrormess", "Success");


        return "/homepage/translate";
    }
	
	@GetMapping("/translate/article")

	public String openArticleGET(Model model, @RequestParam String articleid, @RequestParam String userid) {

			Optional<Articles> article;
			
			userService.updateSession();
			UserList userList = userService.getAuthStatus();
			

			model.addAttribute("loginFormData", new LoginFormData());

			if (!Objects.isNull(userList)) {
				model.addAttribute("userdesc", userList.getUserdesc());
				model.addAttribute("useremail", userList.getEmailid());
			}
			else {
				model.addAttribute("userdesc", "");
				model.addAttribute("useremail", "");
			}
			
			model.addAttribute("loginerrormess", "Success");

			
			article = articleService.getArticle(articleid);
			
			if (article.isPresent()) {
				System.out.println("Found Article Details of ID"+articleid);

				//model.addAttribute("text", article.get().getArticletext());
				//model.addAttribute("articlename", article.get().getArticlename());
				
				Articles articleData = article.get();
				
				System.out.println(articleData.toString());
				model.addAttribute("articleData", articleData);
				
				
				//if (article.get().getArticletrans() != null) {
				//model.addAttribute("transtext", article.get().getArticletrans());

				//model.addAttribute("status", article.get().getTransstat());
			}
			else {
				System.out.println("Article Not found");
			}
				
			 return "/homepage/open-article";
    }
	

	@RequestMapping(value = "/savearticle", method = RequestMethod.POST)
	public String saveArticle(@ModelAttribute("articleData") Articles articleData, //
			Model model) throws Exception
	{
		
		UserList userList = userService.getAuthStatus();

		if (!Objects.isNull(userList)) {
			
			articleData.setTransstat("IN PROGRESS");
			
			articleService.updateArticle(articleData.getArticleid(), articleData);
			return "redirect:/translate/article?articleid="+articleData.getArticleid()+"&userid="+userList.getUserName();
		}
		
		return "redirect:/";
			
	}





	@GetMapping("/signup")
    public String signupVolunteer(Model model) {

	     UserForm form = new UserForm();
	 
	      model.addAttribute("userForm", form);

        return "homepage/signup";
    }
	
	   // This method is called to save the registration information.
	   // @Validated: To ensure that this Form
	   // has been Validated before this method is invoked.
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String saveRegister(Model model, //
		@ModelAttribute("userForm") @Validated UserForm userForm, //
		BindingResult result, //
		final RedirectAttributes redirectAttributes) {
		
		 	// Validate result
	      if (result.hasErrors()) {
	    	  System.out.println("In controller - Has errors");
	    	  
	    	  List<ObjectError> errors = result.getAllErrors();
	    	  
	    	  for (ObjectError error : errors) {
	    		  System.out.println(error.getCode());
	    	  }
	    	  
	         return "homepage/signup";
	      }

			UserList newUser;
			try {
			     newUser = userService.createUserByForm(userForm);
			  }
			  // Other error!!
			  catch (Exception e) {
			     model.addAttribute("errorMessage", "Error: " + e.getMessage());
			     return "error";
			      }
		 
		      redirectAttributes.addFlashAttribute("flashUser", newUser);
		   
		  return "redirect:/signupSuccess";
	}	
	
	@GetMapping("/signupSuccess")
	public String loadSignupSuccess(Model model) {
		return "signupSuccess";
	}

	   
	@GetMapping("/contact")
    public String loadcontact(Model model) {

        // TODO: save project in DB here

        return "/homepage/contact";
    }}
