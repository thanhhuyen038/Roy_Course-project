package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Chapter;
import com.example.apiroy.Repository.BookRepository;
import com.example.apiroy.Repository.ChapterRepository;
import com.example.apiroy.Service.BookService;
import com.example.apiroy.Service.ChapterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Chapter getChapterById(Long id) throws Exception {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(() -> new Exception("Truyện này không tồn tại: " + id));
        return chapter;
    }

    @Override
    public Chapter createChapter(Long bookId, Chapter chapter) {
        Book book = bookRepository.findById(bookId).get();
        chapter.setBook(book);
        chapterRepository.save(chapter);
        if(book.getListChapter() == null){
            book.setListChapter(new ArrayList<>());
        }
        book.getListChapter().add(chapter);
        bookRepository.save(book);
        return chapter;
    }

    @Override
    public Chapter updateChapter(Long bookId, Long chapterId, Chapter chapterDetails) throws Exception {
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow(()-> new Exception("Chương này không tồn tại: " + chapterId));
                // So sánh và cập nhật nội dung chương nếu có thay đổi
        if (!Objects.equals(chapter.getContent(), chapterDetails.getContent())) {
            chapter.setContent(chapterDetails.getContent());
        }
        return chapterRepository.save(chapter);
    }

    @Override
    public Map<String, Boolean> deleteChapter(Long bookId,Long chapterId) throws Exception {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new Exception("Chương này không tồn tại: " + chapterId));

        chapterRepository.delete(chapter);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
