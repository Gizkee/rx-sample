
import com.sample.rx.data.source.remote.ProductModel
import io.reactivex.Single

interface ProductRemoteDataSource {

    fun getProducts(): Single<ProductModel>

}