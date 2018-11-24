package com.example.goncharov01.kotlinerx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                //создали разметку
        setContentView(R.layout.activity_main)
        //подключаем на кнопку слешателя
        button.setOnClickListener{
            //стартRsteam
            startRStream()
        }
    }
    private fun startRStream() {

//Create an Observable//
       //создаем переменную myobservable которая дабавляет обсервебл
        val myObservable = getObservable()

//Create an Observer//
                //создали переменную муобсервер и дабавляем его
        val myObserver = getObserver()

//Subscribe myObserver to myObservable//
     //производителя данных подписываем на потребителя данных
        myObservable
                .subscribe(myObserver)
    }
    //функция гет обсервер принимает в себя ничего выводит обсервер параметрезированным классом стринг
    private fun getObserver(): Observer<String> {
      //выводим обект обсервер
        return object : Observer<String> {
            //переопределяем функцию он сабскрайб которая позволяет нам подписыватся на данные (этот метод можно использовать только в обсервер)
            override fun onSubscribe(d: Disposable) {
            }

//Every time onNext is called, print the value to Android Studio’s Logcat//
             //переопределяем функцию он некст ( позволяет нам обрабатывать каждый элемент потока)
            override fun onNext(s: String) {
                Log.d(TAG, "onNext: $s")
            }

//Called if an exception is thrown//
                 //функция onerror этот метод выполняется если происходит ошибка
            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

//When onComplete is called, print the following to Logcat//
              //этот метод выполняется когда поток будет заверщен
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
    }

//Give myObservable some data to emit//
          // создаем функцию производителя данных который с помощью метода JUST обработает данные и превратит их в поток
    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }
}
