package com.luno.ferreteria.dao;

import com.luno.ferreteria.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedBack extends JpaRepository<FeedBack, Integer> {
}
