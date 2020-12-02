package io.community.translation.munselvom.article;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArticlesService {

	@Autowired
	private ArticlesRepository articlesRepository;
		

	public Optional<Articles> getArticle(String id) {
		
		return articlesRepository.findById(id);
		
	}

	public void addArticle(Articles article) {
		articlesRepository.save(article);
		
	}

	public void updateArticle(String id, Articles article) {
		articlesRepository.save(article);
		}

	public void deleteArticle(String id) {
		articlesRepository.deleteById(id);
		}

}
