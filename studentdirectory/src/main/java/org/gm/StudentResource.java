package org.gm;



import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {
    @Inject StudentRepository studentRepository;

    @GET
    public Response getAll(){
        List<Student> students= studentRepository.listAll();
        return Response.ok(students).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id) {
        return studentRepository.findByIdOptional(id).map(student -> Response.ok(student).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());

    }
    @GET
    @Path("name/{name}")
    public Response getByName(@PathParam("name") String name) {
        return studentRepository.find("name",name).firstResultOptional().map(student -> Response.ok(student).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @GET
    @Path("age/{age}")
    public Response getByAge(@PathParam("age") int age) {
        return studentRepository.find("age",age).firstResultOptional().map(student -> Response.ok(student).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());

    }
    @GET
    @Path("major/{major}")
    public Response getByMajor(@PathParam("major") String major) {
        return studentRepository.find("major",major).firstResultOptional().map(student -> Response.ok(student).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @GET
    @Path("name/{name}/age/{age}")
    public  Response getByNameAge(@PathParam("name") String name, @PathParam("age") int age){
        Student student=studentRepository.findByNameAge(name,age);
        }


    @POST
    @Transactional
    public Response create(Student student) {
        studentRepository.persist(student);
                if (studentRepository.isPersistent(student)) {
            return Response.created(URI.create("/student/" + student.getId())).build();
        }
        return Response.status(NOT_FOUND).build();
    }
    @PUT
    @Path("{id}")
    @Transactional
    public void patchById(@PathParam("id") long id,Student newstudent){
        Student student= studentRepository.findById(id);
        if(student==null)
            return;
        else {
            student.setAge(newstudent.getAge());
            student.setName(newstudent.getName());
            student.setMajor(newstudent.getMajor());
            studentRepository.persist(student);
        }

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = studentRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(BAD_REQUEST).build();
    }
}



