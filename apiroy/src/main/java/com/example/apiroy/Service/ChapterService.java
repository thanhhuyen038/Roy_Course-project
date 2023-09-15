package com.example.apiroy.Service;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {

    Chapter getChapterById(Long chapterId) throws Exception;

    Chapter createChapter(Long bookId, Chapter chapter);

    Chapter updateChapter(Long bookId,Long chapterId, Chapter chapterDetails) throws Exception;

    Map<String, Boolean> deleteChapter(Long bookId,Long chapterId) throws Exception;
}
