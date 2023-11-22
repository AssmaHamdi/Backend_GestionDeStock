package com.hamdi.gestiondestock.services.impl;

import com.hamdi.gestiondestock.dto.ArticleDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.model.Article;
import com.hamdi.gestiondestock.repository.ArticleRepository;
import com.hamdi.gestiondestock.services.ArticleService;
import com.hamdi.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository=articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {

        List<String> errors = ArticleValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("Article is not valid{}", dto);
            throw new InvalidEntityException("Article n'est pas valide", ErrorCodes.ARTICLE_NOT_FOUND, errors);
        }
        return ArticleDto.FromEntity(
                articleRepository.save(ArticleDto.ToEntity(dto)));
    }

    @Override
    public ArticleDto findById(Integer id) {

        if (id==null) {
            log.error("Article Id is null");
            return null;
        }
        Optional<Article> article = articleRepository.findById(id);

        return Optional.of(ArticleDto.FromEntity(article.get())).orElseThrow(() ->

            new EntityNotFoundException("Aucun Article avec l'Id =" +id+ "n'ete trouve dans le BDD",
                    ErrorCodes.ARTICLE_NOT_FOUND )
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        if (!StringUtils.hasLength(codeArticle)) {
            log.error("codeArticle is null");
            return null;
        }
        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);

        return Optional.of(ArticleDto.FromEntity(article.get())).orElseThrow(() ->

                new EntityNotFoundException("Aucun Article avec le codeArticle =" +codeArticle+ "n'ete trouve dans le BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND )
        );
    }

    @Override
    public List<ArticleDto> findAll() {

        return articleRepository.findAll().stream()
                .map(ArticleDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Article Id is null");
            return;
        }
        articleRepository.deleteById(id);

    }
}
