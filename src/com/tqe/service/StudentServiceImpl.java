package com.tqe.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tqe.dao.StudentDao;
import com.tqe.po.Student;
import com.tqe.po.User;

@Service
public class StudentServiceImpl extends BaseService<Student>{
		@Resource
		private StudentDao StudentDao;
		@Override
		public List<Student> findAll() {
			return StudentDao.findAll();
		}
		
		public Student getById(String sid) {
			return StudentDao.getById(sid);
		}
		
		public void save(Student e) {
			StudentDao.save(e);
		}
		
		public boolean saveAll(List<Student> list){
			boolean f = false;
			try {
				if(list!=null){
					for(Student s:list){
						if(s.getSid()!=null){
							save(s);
						}
					}
					f= true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return f;
			
		}

		public Student login(User user) {
			return StudentDao.login(user);
		}
}
