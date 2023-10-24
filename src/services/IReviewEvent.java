package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 import java.util.List;
/**
 *
 * @author ksouri
 * @param <R>
 */
public interface IReviewEvent <R> {
    public void addReviewEvent(R r);
    public void updateReviewEvent(R r);
    public void deleteReviewEvent(R r);
    public List<R> getAllReviewsByEvent(int i);
    public R getReviewEventByUserByEvent(int idUser , int idEvent);
    
}
