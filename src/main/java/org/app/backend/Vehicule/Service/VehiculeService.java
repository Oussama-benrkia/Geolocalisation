package org.app.backend.Vehicule.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.backend.User.Dto.UserResp;
import org.app.backend.User.Model.User;
import org.app.backend.User.repo.IntRepUser;
import org.app.backend.Util.ImgService;
import org.app.backend.Vehicule.Dto.VehiculeRequest;
import org.app.backend.Vehicule.Dto.VehiculeRequestUp;
import org.app.backend.Vehicule.Dto.VehiculeResp;
import org.app.backend.Vehicule.Model.Enum.Type;
import org.app.backend.Vehicule.Model.Vehicule;
import org.app.backend.Vehicule.Rep.VehiculeRep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    private final IntRepUser userrep;
    public List<String> getalltype(){
        return enumToList(Type.values());
    }
    private   <T extends Enum<T>> List<String> enumToList(T[] enumValues) {
        List<String> list = new ArrayList<>();
        for (T enumValue : enumValues) {
            list.add(enumValue.name());
        }
        return list;
    }
    private Type getType(String input) {
        for (Type type : Type.values()) {
            if (type.name().equalsIgnoreCase(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant found for input: " + input);
    }
    public Vehicule findByMatricule(String matricule) {
        return vehiculeRep.findByMatricule(matricule);
    }
    public List<Vehicule> findByMatriculeAndModele(String matricule, String modele) {
        return vehiculeRep.findByMatriculeAndModele(matricule, modele);
    }
    public List<Vehicule> findAll(String se) {
        if(!se.isEmpty()) {
            return vehiculeRep.findAllByNomContainingOrMatriculeContainingOrModeleContaining(se,se,se);
        }
        return vehiculeRep.findAll();
    }
    public Page<Vehicule> findAllpage(String se,Pageable p) {
        if(!se.isEmpty()) {
            return vehiculeRep.findAllByNomContainingOrMatriculeContainingOrModeleContaining(se,se,se,p);
        }
        return vehiculeRep.findAll(p);
    }
    public Vehicule createVehicule(VehiculeRequest vehiculereq) {
        UserDetails user= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User us=userrep.findByEmail(user.getUsername()).orElse(null);
        Vehicule v = new Vehicule();
        Vehicule existingVehicule = vehiculeRep.findByMatricule(vehiculereq.getMatricule());
        if (existingVehicule != null) {
            return null;
        }
        String img="";
        if(vehiculereq.getImage()!=null){
            img=imgService.addimage(vehiculereq.getImage(),"Cars");
        }
        v.setImage(img);
        v.setType(getType(vehiculereq.getType()));
        v.setMatricule(vehiculereq.getMatricule());
        v.setEtat(vehiculereq.isEtat());
        v.setNom(vehiculereq.getNom());
        v.setModele(vehiculereq.getModele());
        v.setUser(us);
       return  vehiculeRep.save(v);
    }
    @Transactional
    public Vehicule editVehicule(long id, VehiculeRequestUp vehiculerequp) {
        Optional<Vehicule> existingVehiculeOptional = vehiculeRep.findById(id);
        if (existingVehiculeOptional.isEmpty()) {
            return null;
        }
        Vehicule existingVehicule = existingVehiculeOptional.get();
        if (!vehiculerequp.getMatricule().isEmpty()) {
            existingVehicule.setMatricule(vehiculerequp.getMatricule());
        }
        if (!vehiculerequp.getNom().isEmpty()) {
            existingVehicule.setNom(vehiculerequp.getNom());
        }
        if (!vehiculerequp.getModele().isEmpty()) {
            existingVehicule.setModele(vehiculerequp.getModele());
        }
        if (!vehiculerequp.isEtat()) {
            existingVehicule.setEtat(vehiculerequp.isEtat());
        }
        if (vehiculerequp.getImage() != null) {
            if (!existingVehicule.getImage().isEmpty()) {
                imgService.deleteimage(existingVehicule.getImage());
            }
            String newImageFileName = imgService.addimage(vehiculerequp.getImage(), "Cars");
            existingVehicule.setImage(newImageFileName);
        }
        if(vehiculerequp.getType()!=null){
            existingVehicule.setType(getType(vehiculerequp.getType()));
        }
        return vehiculeRep.save(existingVehicule);
    }
    @Transactional
    public Vehicule deleteVehicule(long id) {
        Vehicule old_v = vehiculeRep.findById(id).orElse(null);
        if (old_v != null) {
            if (!old_v.getImage().isEmpty()) {
                imgService.deleteimage(old_v.getImage());
            }
            vehiculeRep.delete(old_v);
            return old_v;
        }
        return null;
    }
    private final VehiculeRep vehiculeRep;
    private final ImgService imgService;

}
