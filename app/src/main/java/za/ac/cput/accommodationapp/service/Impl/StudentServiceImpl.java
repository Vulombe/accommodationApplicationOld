package za.ac.cput.accommodationapp.service.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import za.ac.cput.accommodationapp.conf.util.App;
import za.ac.cput.accommodationapp.domain.Student;
import za.ac.cput.accommodationapp.repository.Impl.StudentRepositoryImpl;
import za.ac.cput.accommodationapp.repository.StudentRepository;
import za.ac.cput.accommodationapp.service.StudentService;

public class StudentServiceImpl extends Service implements StudentService{

    private final IBinder localBinder = new StudentServiceLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    StudentRepository repository;

    private static StudentServiceImpl service = null;

    public static StudentServiceImpl getInstance() {
        if (service == null)
            service = new StudentServiceImpl();
        return service;
    }
    public class StudentServiceLocalBinder extends Binder {
        public StudentServiceImpl getService() {
            return StudentServiceImpl.this;
        }
    }
    public StudentServiceImpl()
    {
        repository = new StudentRepositoryImpl(App.getAppContext());
    }

    @Override
    public Student getStudentByID(Long id) {
        if(repository.findById(id) == null)
            return null;
        else
            return repository.findById(id);
    }

    public Student saveStudent(Student entity)
    {
        return repository.save(entity);
    }

    public Set<Student> findAll()
    {
        return repository.findAll();
    }
}
