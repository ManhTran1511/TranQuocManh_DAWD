package com.example.tranquocmanh_dawd.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    long insertEmployee(Employee employee);

    @Update
    int updateEmployee(Employee employee);

    @Delete
    int deleteEmployee(Employee employee);

    @Query("SELECT * FROM Employee")
    List<Employee> getAllEmployee();

    @Query("SELECT * FROM Employee WHERE id = :id")
    Employee findEmployeeById(int id);
}
