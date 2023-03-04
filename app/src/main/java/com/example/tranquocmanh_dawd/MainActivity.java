package com.example.tranquocmanh_dawd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tranquocmanh_dawd.Adapter.EmployeeAdapter;
import com.example.tranquocmanh_dawd.Database.AppDatabase;
import com.example.tranquocmanh_dawd.Database.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TAG";

    EditText edName, edSalary, edDesignation;
    Button btnAdd, btnUpdate, btnDelete;

    RecyclerView rvEmployee;

    List<Employee> listEmployee;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getAppDatabase(this);

        edName = findViewById(R.id.edName);
        edSalary = findViewById(R.id.edSalary);
        edDesignation = findViewById(R.id.edDesignation);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        listEmployee = db.employeeDao().getAllEmployee();

        EmployeeAdapter adapter = new EmployeeAdapter(this, listEmployee);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rvEmployee = findViewById(R.id.rvEmployee);
        rvEmployee.setLayoutManager(layoutManager);
        rvEmployee.setAdapter(adapter);

    }

    private void onAdd() {
        if (!validate()) {
            return;
        }
        Employee employee = new Employee();
        employee.name = edName.getText().toString();
        employee.salary = edSalary.getText().toString();
        employee.designation = edDesignation.getText().toString();
        long id = db.employeeDao().insertEmployee(employee);
        if (id > 0) {
            Toast.makeText(this, "Add Success", Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, "onRegister: "+ employee.name + "-" + employee.salary + "-" + employee.designation);
        goToMain();
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void onUpdate(int id) {
        if(!validate()) {
            return;
        }

        Employee employee = db.employeeDao().findEmployeeById(id);
        employee.name = edName.getText().toString();
        employee.salary = edSalary.getText().toString();
        employee.designation = edDesignation.getText().toString();
        long us = db.employeeDao().updateEmployee(employee);

        if (us > 0) {
            Toast.makeText(this, "Update Success", Toast.LENGTH_LONG).show();
        }
    }

    private void onDelete(int id) {
        Employee employee = db.employeeDao().findEmployeeById(id);
        int us = db.employeeDao().deleteEmployee(employee);

        if (us > 0) {
            Toast.makeText(this, "Delete Success", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validate() {
        String mes = null;
        if (edName.getText().toString().trim().isEmpty()) {
            mes = "Chua nhap ten!";
        } else if (edSalary.getText().toString().trim().isEmpty()) {
            mes = "Chua nhap luong!";
        } else if (edDesignation.getText().toString().trim().isEmpty()) {
            mes = "Chua nhap vi tri!";
        }

        if(mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                onAdd();
                break;
            case R.id.btnUpdate:
//                onUpdate(idEmployee);
                break;
            case R.id.btnDelete:
//                onDelete(idEmployee);
                break;
            default:
                break;
        }
    }
}