package com.luno.ferreteria.service.ServiceImp;

import com.luno.ferreteria.dao.IFeedBack;
import com.luno.ferreteria.entity.FeedBack;
import com.luno.ferreteria.service.IFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService implements IFeedBackService {

    @Autowired
    private IFeedBack feedDao;


    @Override
    public FeedBack createFeedBack(FeedBack feedBack) {
        try {

            return feedDao.save(feedBack);
        } catch (Exception e){
            return null;
        }
    }
}
