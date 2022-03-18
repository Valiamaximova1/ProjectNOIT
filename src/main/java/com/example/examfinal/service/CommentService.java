package com.example.examfinal.service;

import com.example.examfinal.model.service.CommentServiceModel;
import com.example.examfinal.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    CommentViewModel createComment(CommentServiceModel commentServiceModel);


    List<CommentViewModel> getComments(Long routeId);
}
