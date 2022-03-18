package com.example.examfinal.service.impl;

import com.example.examfinal.model.entity.Comment;
import com.example.examfinal.model.service.CommentServiceModel;
import com.example.examfinal.model.view.CommentViewModel;
import com.example.examfinal.repository.CommentRepository;
import com.example.examfinal.repository.PlaceRepository;
import com.example.examfinal.repository.UserRepository;
import com.example.examfinal.service.CommentService;
import com.example.examfinal.service.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(PlaceRepository placeRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {

        Objects.requireNonNull(commentServiceModel.getCreator());

        var place = placeRepository.
                findById(commentServiceModel.getPlaceId()).
                orElseThrow(() -> new ObjectNotFoundException("Route with id " + commentServiceModel.getPlaceId() + " not found!"));

        var author = userRepository.
                findByUsername(commentServiceModel.getCreator()).
                orElseThrow(() -> new ObjectNotFoundException("User with eamil " + commentServiceModel.getCreator() + " not found!"));

        Comment newComment = new Comment();
        newComment.setApproved(true);
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(Instant.now());
        newComment.setPlace(place);
        newComment.setUser(author);

        Comment savedComment = commentRepository.save(newComment);

        return mapAsComment(savedComment);
    }


    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long placeId) {
        var placeOpt = placeRepository.
                findById(placeId);

        if (placeOpt.isEmpty()) {
            throw new ObjectNotFoundException("Route with id " + placeId + " was not found!");
        }

        return placeOpt.
                get().
                getComments().
                stream().
                map(this::mapAsComment).
                collect(Collectors.toList());
    }

    private CommentViewModel mapAsComment(Comment commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.setCommentId(commentEntity.getId());
        commentViewModel.setCanApprove(true);
        commentViewModel.setCanDelete(true);
        commentViewModel.setCreated(commentEntity.getCreated());
        commentViewModel.setMessage(commentEntity.getTextContent());
        commentViewModel.setUser(commentEntity.getUser().getFirstName());


        return commentViewModel;
    }
}
