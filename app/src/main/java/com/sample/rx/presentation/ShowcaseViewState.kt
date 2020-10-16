package com.sample.rx.presentation

import com.sample.rx.domain.model.Product
import com.sample.rx.domain.model.ProductCategory
import com.sample.rx.presentation.ShowcaseViewState.ShowcaseContainerState.ScannerMessage


sealed class ShowcaseViewState {

    data class Content(
        var categories: List<ProductCategory> = emptyList(),
        var currentCategory: ProductCategory? = null,
        var containerState: ShowcaseContainerState = ScannerMessage
    ) : ShowcaseViewState()

    sealed class ShowcaseContainerState {
        data class ProductsState(val products: List<Product>) : ShowcaseContainerState()
        object ScannerMessage : ShowcaseContainerState()
        object Empty : ShowcaseContainerState()
    }

}
