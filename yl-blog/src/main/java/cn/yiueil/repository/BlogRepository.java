package cn.yiueil.repository;

import cn.yiueil.entity.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Integer> {
}