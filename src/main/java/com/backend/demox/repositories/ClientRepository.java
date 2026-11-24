package com.backend.demox.repositories;

import com.backend.demox.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//public interface ClientRepository extends CrudRepository<Client,Long> {
public interface ClientRepository extends JpaRepository<Client,Long> {

    //List<Client> findAll();
    List<Client> findByName(String name);
    Optional<Client> findById(Long id);


    List<Client> findByNameAndSdname (String namepar, String sdnamepar);
    List<Client> findByNameOrSdname (String namepar, String sdnamepar);

    List<Client> findFirst3ByName (String namepar);

    List <Client> findByNameIgnoreCase (String namepar);
    Client findFirstByName (String namepar) ;

    // findTop3ByNameContaining
    List<Client> findFirst3ByNameContaining (String namepar) ;

    List <Client> findByIdBetween (long idfrom , long idto) ;



    // JPQL
    @Query("SELECT cl FROM Client cl WHERE cl.sdname LIKE ?1")
    List<Client> findBySdnameLike (String sdnamelike);


    // SQL
    @Query(value="SELECT * FROM public.client cl WHERE cl.sdname LIKE ?1", nativeQuery=true)
    List<Client> findBySdnameLikeSQLbased (String sdnamelike);


    // SQL
    @Query(value="SELECT * FROM public.client cl WHERE cl.name LIKE :p_name AND cl.sdname LIKE :p_sdname", nativeQuery=true)
    List<Client> findByLikesSQLbased (@Param("p_name") String namelike, @Param("p_sdname") String sdnamelike);


//    @Modifying
//    @Query (value = "Update public.client set act = 1 where id = ?1", nativeQuery = true)
//    int SoftDelete (Long id);
    /*

    // JPQL 4 dependent elements
    @Query(value="SELECT cl FROM Client cl JOIN FETCH cl.products WHERE cl.id = ?1")
    List<Client> findFullById (Long id);


     */


    // JPQL
    @Query("SELECT cl FROM Client cl WHERE cl.name LIKE ?1")
    List<Client> findByNameLikeJPQLbased (String namelike);

    // SQL
    @Query(value="SELECT * FROM public.client cl WHERE cl.name LIKE ?1", nativeQuery=true)
    List<Client> findByNameLikeSQLbased (String namelike);



}
