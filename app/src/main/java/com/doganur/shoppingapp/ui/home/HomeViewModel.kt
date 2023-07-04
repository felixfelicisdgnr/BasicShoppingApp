package com.doganur.shoppingapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doganur.shoppingapp.data.ProductRepository
import com.doganur.shoppingapp.data.model.Product

class HomeViewModel : ViewModel() {

    private val productRepository = ProductRepository()
    /*
    farklı farklı viewModellerde bu repository!i kullanacağız. 11. satır her çalıştığında ne oluyor?ProductRepostiory tekrar tekrar oluşuyor.
    3 fragment olsa 3 viewModel olacak ve 3 kere oluşacak, Repositoryde gereksiz yere 3 kere oluşmuş olacak ve gereksiz performans kaybı, küçük projelerde pek önemi yok
    ama daha büyük projelerde performans kaybına sebep olacak. Hilt ile Sinleton mantığında bir kere oluşturup
    "class HomeViewModel(BURAYA ENJECT EDİP BURADAN REPOSİTORY ALMAK ProductRepository) : ViewModel()" { ÇOK DAHA MANTIKLI
    ??application Class oluşturup comp object oluşturup instant oluşturarak da yapılabilir??
       -- init her class'da çalışan ilk scope init scope'dur!! App açıldığı gibi ekrana bastırmak istiyorum ee init bloğunda çağırmam çok daha mantıklı.
       init kullanmazsam homeFragmentta homeViewModel.products şeklinde çağırmam lazım sayfa açıldığı gibi yine istek atmış olacağım, bir başka fragmentta gittiğimde yani sayfa geçişi
       yaptığımda bu veriler tekrar sayfaya yüklenecek çünkü satır tekrar çalışacak. OnViewCreated'da bunu tanımlamak view create olduğunda bu isteği tekrar atmasına sebep oluyor.
       ama init olarak ViewModel'da tanıttığımda başka bir sayfaya gitsem dahi VİewModel ölmediği için tekrar istek atılmayacak, verileri MutableLiveData'dan çekip yazdırmış olacağım.
       Sadece observe ettiğim yer çalışmış oalcak.
     */

    private var _productList = MutableLiveData<List<Product>?>()//doldurmak için

    val productList : LiveData<List<Product>?> //dinlemek için
        get() = _productList

    init {
        products()
    }

    fun products(){
        productRepository.getProducts()
        _productList = productRepository.productList
    }

    fun searchProduct(query : String) {
        productRepository.searchProduct(query)
        _productList = productRepository.productList
    }
}