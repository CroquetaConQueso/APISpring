package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.SucursalDTO;
import org.example.exception.NotFoundException;
import org.example.mapper.Mapper;
import org.example.modelos.Sucursal;
import org.example.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SucursalService implements SucursalServiceIMP {


    private SucursalRepository repoSuc;

    @Override
    public List<SucursalDTO> findSucursals() {
        return repoSuc.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO findSucursal(Long id) {
        return Mapper.toDTO(repoSuc.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado la sucursal")));
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDto) {
        Sucursal sucursal = Sucursal.builder()
                .nombreSucursal(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        repoSuc.save(sucursal);

        return Mapper.toDTO(sucursal);
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto) {
        Sucursal sucursal = repoSuc.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado la sucursal"));

        sucursal.setNombreSucursal(sucursalDto.getNombre());
        sucursal.setDireccion(sucursalDto.getDireccion());

        repoSuc.save(sucursal);
        return Mapper.toDTO(sucursal);
    }

    @Override
    public void borrarSucursal(Long id) {
        if(!repoSuc.existsById(id)){
            throw new NotFoundException("No se ha encontra la sucursal");
        }else{
            repoSuc.deleteById(id);
        }
    }
}
