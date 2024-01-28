package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.ProductDto;
import com.eshop.eshop.entity.ManufacturerEntity;
import com.eshop.eshop.entity.MerchantStoreEntity;
import com.eshop.eshop.entity.product.ProductEntity;
import com.eshop.eshop.entity.product.variant.ProductVariant;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.mapper.ManufacturerMapper;
import com.eshop.eshop.mapper.MerchantMapper;
import com.eshop.eshop.mapper.ProductMapper;
import com.eshop.eshop.repository.ProductRepository;
import com.eshop.eshop.service.ManufacturerService;
import com.eshop.eshop.service.MerchantService;
import com.eshop.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private MerchantService merchantService;

    private ProductMapper productMapper;

    private ManufacturerMapper manufacturerMapper;
    private MerchantMapper merchantMapper;

    @Override
    public ProductDto createProduct(ProductDto product) {

        ManufacturerEntity manufacturer = manufacturerService.getManufacturerById(product.getManufacturer().getId());
        MerchantStoreEntity merchantStore = merchantMapper.convertToEntity(merchantService.getMerchantById(product.getMerchantStore().getId()));

        product.setManufacturer(manufacturerMapper.convertToDto(manufacturer));
        product.setMerchantStore(merchantMapper.convertToDto(merchantStore));

        ProductEntity newProduct = productMapper.convertToEntity(product);

        return productMapper.convertToDto(productRepository.save(newProduct));
    }

    @Override
    public List<ProductDto> getProductList() {

        List<ProductEntity> listOfTheProducts = productRepository.findByDeletedFalse();

        return listOfTheProducts.stream().map((product) -> productMapper.convertToDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(long id) {
        ProductEntity product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));
        return productMapper.convertToDto(product);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        ProductEntity toUpdate = productRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        ManufacturerEntity manufacturer = manufacturerService.getManufacturerById(productDto.getManufacturer().getId());

        toUpdate.setProductQuantity(productDto.getProductQuantity());
        toUpdate.setProductDateAvailable(productDto.getProductDateAvailable());
        toUpdate.setAvailable(productDto.getAvailable());
        toUpdate.setCollection(productDto.getCollection());
        toUpdate.setDescription(productDto.getDescription());
        toUpdate.setPrice(productDto.getPrice());
        toUpdate.setProductQuantityOrderMax(productDto.getProductQuantityOrderMax());
        toUpdate.setProductQuantityOrderMin(productDto.getProductQuantityOrderMin());
        toUpdate.setRegion(productDto.getRegion());
        toUpdate.setSku(productDto.getSku());
        toUpdate.setThumbnail(productDto.getThumbnail());
        toUpdate.setTitle(productDto.getTitle());
        toUpdate.setManufacturer(manufacturer);

        ProductEntity updatedProduct = productRepository.save((toUpdate));

        return productMapper.convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        ProductEntity product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));
        product.setDeleted(true);
        productRepository.save(product);
    }

}
