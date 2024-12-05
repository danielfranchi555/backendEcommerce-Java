package com.ecommerce.demo.service.product;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.dto.SizeStockDto;
import com.ecommerce.demo.error.CategoryNotFoundException;
import com.ecommerce.demo.error.ProductNotFoundException;
import com.ecommerce.demo.models.*;
import com.ecommerce.demo.repositories.CategoryRepository;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.repositories.ProductSizeRepository;
import com.ecommerce.demo.repositories.SizesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SizesRepository sizesRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;


    @Override
    public List<ProductDto> getProducts() {

        List<ProductDto> products = new ArrayList<>();
        Iterable<Product> productsFromDB = productRepository.findAll();

        for (Product prod : productsFromDB) {
            ProductDto product = new ProductDto();
            product.setId_product(prod.getId_product());
            product.setName(prod.getName());
            product.setPrice(prod.getPrice());
            product.setCategory(prod.getCategory().getName_category());
            product.setDescription(prod.getDescription());

            // get images
            List<String> images = prod.getImages().stream()
                    .map(ProductsImage::getImageUrl)
                    .toList();
            product.setImages(images);

            // Obtener talles y stocks
            List<SizeStockDto> sizes = prod.getProductSizes().stream()
                    .map(ps -> new SizeStockDto(ps.getSize().getSize_name(), ps.getStock()))
                    .toList();
            product.setSizes(sizes);

            System.out.println(sizes);

            products.add(product);
        }
        return products;
    }

    @Override
    public ProductDto getProduct(Integer id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("product not found");
        }
        Product prod = product.get();
        ProductDto productDto = new ProductDto();
        productDto.setName(prod.getName());
        productDto.setPrice(prod.getPrice());
        productDto.setCategory(prod.getCategory().getName_category());
        productDto.setDescription(prod.getDescription());
        productDto.setId_product(prod.getId_product());


        List<String> images = prod.getImages().stream()
                .map(ProductsImage::getImageUrl)
                .toList();
        productDto.setImages(images);
        return productDto;
    }


    @Override
    public void addProduct(Product product) throws CategoryNotFoundException {
        // Verificar que la categoría exista
        Optional<Category> categoryOptional = Optional.ofNullable(
                categoryRepository.findByName_category(product.getCategory().getName_category())
        );
        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException("Category not found");
        }

        // Asociar la categoría al producto
        Category category = categoryOptional.get();
        product.setCategory(category);

        // Verificar que el producto incluya imágenes
        if (product.getImages() == null || product.getImages().isEmpty()) {
            throw new IllegalArgumentException("El producto debe incluir al menos una imagen.");
        }

        // Establecer la relación bidireccional entre producto e imágenes
        for (ProductsImage image : product.getImages()) {
            image.setProduct(product);
        }

        // Guardar el producto (con persistencia en cascada)
        productRepository.save(product);

        // Insertar directamente las combinaciones de tamaño y stock para el nuevo producto
        List<ProductSize> productSizes = new ArrayList<>();

        for (ProductSize productSize : product.getProductSizes()) {
            if (productSize.getSize() == null || productSize.getSize().getSize_name() == null) {
                throw new IllegalArgumentException("Cada ProductSize debe incluir un objeto Sizes con un size_name válido.");
            }

            // Buscar el tamaño por su nombre (size_name)
            Optional<Sizes> sizeOptional = sizesRepository.findBySizeName(productSize.getSize().getSize_name());
            if (sizeOptional.isEmpty()) {
                throw new IllegalArgumentException("Size not found: " + productSize.getSize().getSize_name());
            }

            Sizes size = sizeOptional.get();  // Obtener el objeto Sizes correspondiente

            // Crear una nueva instancia de ProductSize y asociar el producto y tamaño
            ProductSize newProductSize = new ProductSize();
            newProductSize.setProduct(product);  // Asignar el producto
            newProductSize.setSize(size);  // Asignar el tamaño encontrado
            newProductSize.setStock(productSize.getStock());  // Asignar el stock

            // Agregar la nueva combinación de ProductSize a la lista
            productSizes.add(newProductSize);
        }

        // Guardar todas las combinaciones de tamaño y stock
        productSizeRepository.saveAll(productSizes);
    }



    @Override
    public void updateProduct(Integer id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> productExistOptional = productRepository.findById(id);

        if (productExistOptional.isPresent()) {
            Product productExist = productExistOptional.get();
            productExist.setName(product.getName());
            productExist.setDescription(product.getDescription());
            productExist.setPrice(product.getPrice());
            productExist.setStock(product.getStock());

            if (product.getCategory() != null) {
                Optional<Category> categoryOptional = Optional.ofNullable(
                        categoryRepository.findByName_category(product.getCategory().getName_category())
                );

                if (categoryOptional.isEmpty()) {
                    throw new CategoryNotFoundException("category not found");
                }

                productExist.setCategory(categoryOptional.get());

                if (product.getImages() != null) {
                    // Limpiar imágenes existentes
                    productExist.getImages().clear();

                    // Asignar las nuevas imágenes
                    for (ProductsImage newImage : productExist.getImages()) {
                        newImage.setProduct(productExist); // Establecer relación bidireccional
                        productExist.getImages().add(newImage);
                    }
                }
            }
            productRepository.save(productExist);
        } else {
            throw new ProductNotFoundException("product not found");
        }


    }

    @Override
    public void deleteProduct(Integer id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product not Found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> findByName(String name) {
        List<Product> products = productRepository.findByName(name);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product prod : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId_product(prod.getId_product());
            productDto.setName(prod.getName());
            productDto.setDescription(prod.getDescription());
            productDto.setPrice(prod.getPrice());
            productDto.setCategory(prod.getCategory().getName_category());
            List<String> images = prod.getImages().stream()
                    .map(ProductsImage::getImageUrl)
                    .toList();

            productDto.setImages(images);
            productDtos.add(productDto);
        }


        return productDtos;
    }
}
