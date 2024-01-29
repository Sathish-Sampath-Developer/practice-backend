package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.product.ProductDto;
import com.eshop.eshop.entity.ManufacturerEntity;
import com.eshop.eshop.entity.MerchantStoreEntity;
import com.eshop.eshop.entity.product.ProductEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.mapper.manufacturer.ManufacturerMapper;
import com.eshop.eshop.mapper.merchant.MerchantMapper;
import com.eshop.eshop.mapper.product.ProductMapper;
import com.eshop.eshop.repository.ManufacturerRepository;
import com.eshop.eshop.repository.MerchantRepository;
import com.eshop.eshop.repository.ProductRepository;
import com.eshop.eshop.service.AuthService;
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
    private ProductRepository productRepository;
    private ManufacturerRepository manufacturerRepository;
    private MerchantRepository merchantRepository;
    private AuthService authService;
    private ProductMapper productMapper;
    private ManufacturerMapper manufacturerMapper;
    private MerchantMapper merchantMapper;

    @Override
    public ProductDto createProduct(ProductDto product) {

        ManufacturerEntity manufacturer = manufacturerRepository.findById(product.getManufacturer().getId()).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id !."));

        MerchantStoreEntity merchantStore = merchantRepository.findById(product.getMerchantStore().getId()).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Merchant was not found given id !."));

        product.setCreatedBy(authService.getAuthenticatedUsername());
        product.setManufacturer(manufacturerMapper.convertToDto(manufacturer));
        product.setMerchantStore(merchantMapper.convertToDto(merchantStore));

        ProductEntity newProduct = productRepository.save(productMapper.convertToEntity(product));

        List<ProductEntity> manufacturerProducts = manufacturer.getProducts();
        List<ProductEntity> merchantProducts = merchantStore.getProducts();

        manufacturerProducts.add(newProduct);
        merchantProducts.add(newProduct);

        manufacturer.setProducts(manufacturerProducts);
        merchantStore.setProducts(manufacturerProducts);

        manufacturerRepository.save(manufacturer);
        merchantRepository.save(merchantStore);

        return productMapper.convertToDto(newProduct);
    }

    @Override
    public List<ProductDto> getProductList() {

        List<ProductEntity> listOfTheProducts = productRepository.findByDeletedFalse();

        return listOfTheProducts.stream().map((product) -> productMapper.convertToDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        return productMapper.convertToDto(product);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {

        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        ManufacturerEntity manufacturer = manufacturerRepository.findById(productDto.getManufacturer().getId()).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id !."));

        MerchantStoreEntity merchantStore = merchantRepository.findById(productDto.getMerchantStore().getId()).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Merchant store was not found given id !."));

        ProductEntity updatedProduct = productMapper.convertToEntity(productDto);

        updatedProduct.setManufacturer(manufacturer);
        updatedProduct.setMerchantStore(merchantStore);
        updatedProduct.setModifiedBy(authService.getAuthenticatedUsername());

        return productMapper.convertToDto(productRepository.save(updatedProduct));
    }

    @Override
    public void deleteProduct(long id) {
        ProductEntity product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));
        product.setDeleted(true);
        productRepository.save(product);
    }

}
