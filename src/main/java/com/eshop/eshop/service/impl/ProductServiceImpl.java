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
import com.eshop.eshop.service.ProductService;
import lombok.AllArgsConstructor;
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

        List<ProductEntity> merchantProducts = merchantStore.getProducts();
        List<ProductEntity> manufacturerProducts = manufacturer.getProducts();

        merchantProducts.add(newProduct);
        manufacturerProducts.add(newProduct);

        merchantStore.setProducts(merchantProducts);
        manufacturer.setProducts(manufacturerProducts);

        merchantRepository.save(merchantStore);
        manufacturerRepository.save(manufacturer);

        return productMapper.convertToDto(newProduct);
    }

    @Override
    public List<ProductDto> getProductList() {

        List<ProductEntity> listOfTheProducts = productRepository.findAll();

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

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setSku(productDto.getSku());
        product.setThumbnail(productDto.getThumbnail());
        product.setLength(productDto.getLength());
        product.setWidth(productDto.getWidth());
        product.setHeight(productDto.getHeight());
        product.setWeight(productDto.getWeight());
        product.setProductQuantity(productDto.getProductQuantity());
        product.setProductDateAvailable(productDto.getProductDateAvailable());
        product.setRegion(productDto.getRegion());
        product.setAvailable(productDto.getAvailable());
        product.setProductQuantityOrderMin(productDto.getProductQuantityOrderMin());
        product.setProductQuantityOrderMax(productDto.getProductQuantityOrderMax());
        product.setCollection(productDto.getCollection());
        product.setSeUrl(productDto.getSeUrl());
        product.setMetaTagTitle(productDto.getMetaTagTitle());
        product.setMetaTagKeywords(productDto.getMetaTagKeywords());
        product.setMetaTagDescription(productDto.getMetaTagDescription());
        product.setManufacturer(manufacturer);
        product.setMerchantStore(merchantStore);
        product.setDateCreated(productDto.getDateCreated());
        product.setDateModified(productDto.getDateModified());
        product.setCreatedBy(productDto.getCreatedBy());
        product.setModifiedBy(authService.getAuthenticatedUsername());

        return productMapper.convertToDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));
        productRepository.delete(product);
    }

}
