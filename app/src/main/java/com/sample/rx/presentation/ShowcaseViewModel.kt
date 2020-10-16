package com.sample.rx.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.rx.domain.model.Product
import com.sample.rx.domain.model.ProductCategory
import com.sample.rx.domain.repository.ProductRepository
import com.sample.rx.presentation.ShowcaseViewState.Content
import com.sample.rx.presentation.ShowcaseViewState.ShowcaseContainerState.Empty
import com.sample.rx.presentation.ShowcaseViewState.ShowcaseContainerState.ProductsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ShowcaseViewModel(
    private val productsRepository: ProductRepository
) : ViewModel() {

    val viewState = MutableLiveData<ShowcaseViewState>()

    private var contentState = Content()
        set(value) {
            field = value
            viewState.value = field
        }

    private val compositeDisposable = CompositeDisposable()

    fun init() {
        getCategories()
    }

    fun onCategoryClick(category: ProductCategory) {
        if (category != contentState.currentCategory) {
            contentState = contentState.copy(currentCategory = category)
            getProductsByCategory(category)
        }
    }

    private fun getCategories() {
        compositeDisposable.add(
                productsRepository.getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { categories -> handleCategoriesSuccess(categories) },
                                { error -> handleError(error) }
                        )
        )
    }

    private fun getProductsByCategory(category: ProductCategory) {
        compositeDisposable.add(
                productsRepository.getProductsByCategoryId(category.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { products -> handleProductsSuccess(products) },
                                { error -> handleError(error) }
                        )
        )
    }

    private fun handleCategoriesSuccess(categories: List<ProductCategory>) {
        contentState = contentState.copy(categories = categories)
    }

    private fun handleProductsSuccess(products: List<Product>) {
        contentState = if (products.isNotEmpty()) {
            contentState.copy(containerState = ProductsState(products))
        } else {
            contentState.copy(containerState = Empty)
        }
    }

    private fun handleError(error: Throwable) {}

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}