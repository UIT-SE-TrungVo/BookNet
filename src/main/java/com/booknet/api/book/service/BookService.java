package com.booknet.api.book.service;

import com.booknet.api.book.model.BookModel;
import com.booknet.api.book.repository.BookRepository;
import com.booknet.api.book.request.BookCreateRequest;
import com.booknet.api.book.request.BookNotifyRequest;
import com.booknet.api.book.request.BookUpdateRequest;
import com.booknet.constants.EvId;
import com.booknet.constants.IdPrefix;
import com.booknet.system.EventCenter;
import com.booknet.utils.IdGenerator;
import com.booknet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookRepository bookRepository;

    public BookModel createBook(BookCreateRequest reqData) {
        String newId = IdGenerator.createNew(IdPrefix.SAMPLE);
        Integer number = reqData.getNumber();
        String text = reqData.getText();

        BookModel newDemoNumber = new BookModel(newId, number, text);
        bookRepository.insert(newDemoNumber);

//        logger.info("create BookModel success {}", Utils.json.stringify(newDemoNumber));
        return newDemoNumber;
    }

    public Collection<BookModel> getAllBooks() {
        Collection<BookModel> books = bookRepository.findAll();
        logger.info("get all BookModel {}", Utils.json.stringify(books));
        return books;
    }

    public BookModel getSample(String id) {
        BookModel book = bookRepository.findBy_id(id).orElse(null);
        logger.info("get BookModel with id {} {}", id, Utils.json.stringify(book));
        return book;
    }

    public BookModel updateBook(String id, BookUpdateRequest reqData) {
        BookModel dbValue = this.getSample(id);

        if (dbValue != null) {
            String jsonOldData = Utils.json.stringify(dbValue);
            dbValue.setNumber(reqData.getNumber());
            dbValue.setText(reqData.getText());
            bookRepository.save(dbValue);
            logger.info("update BookModel success OLD: {} - NEW: {}"
                    , jsonOldData
                    , Utils.json.stringify(reqData)
            );
            return dbValue;
        } else {
            logger.info("cannot update non-exist BookModel");
            return null;
        }
    }

    public BookModel removeBook(String id) {
        BookModel deleteData = this.getSample(id);
        if (deleteData != null) {
            bookRepository.delete(deleteData);
            logger.info("remove BookModel success {}", Utils.json.stringify(deleteData));
            return deleteData;
        } else {
            logger.info("cannot delete non-exist BookModel");
            return null;
        }
    }

    public void doNotify() {
        EventCenter.pub(EvId.SAMPLE_EVENT);
    }

    public void doNotifyWithArgument(@NotNull BookNotifyRequest req) {
        EventCenter.pub(EvId.SAMPLE_EVENT_WITH_ARGS, req.getNumber());
    }

    public void onNotified() {
        logger.info("has been notified");
    }

    public void onNotifiedWithArgument(Integer number) {
        logger.info("has been notified with argument {}", number);
    }
}