package com.tutor.taskmanagement.task.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class TaskDTO {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Long id;
    @NotNull
    @Size(min = 5, max = 100)
    private String taskName;
    @NotEmpty
    private String issueDate;
    @NotEmpty
    private String completionDate;

    /*String to Date Conversion (DTO->Entity)*/
    public Date getIssueDateConverted() throws ParseException {
        return sdf.parse(this.issueDate);
    }

    public Date getCompletionDateConverted() throws ParseException {
        return sdf.parse(this.completionDate);
    }

    /*Date to String conversion (Entity->DTO)*/
    public void setIssueDateConverted(Date date) {
        this.issueDate = sdf.format(date);
    }

    public void setCompletionDateConverted(Date date) {
        this.completionDate = sdf.format(date);
    }



    /*date related methods handle the date conversion back and forth between the client and the server*/
    /*public Date getIssueDateConverted() throws ParseException {
        return sdf.parse(this.issueDate);
    }

    public void setIssuedDate(Date date) {
        this.issueDate = sdf.format(date);
    }

    public Date getCompletionDateConverted() throws ParseException {
        return sdf.parse(this.completionDate);
    }

    public void setCompletedDate(Date date) {
        this.completionDate = sdf.format(date);
    }*/
}
