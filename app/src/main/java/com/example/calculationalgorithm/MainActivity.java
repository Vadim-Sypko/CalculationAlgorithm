package com.example.calculationalgorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    float telescope = 14000; // стоимость телескопа
    int account = 1000;  // счет пользователя
    float stependia = 2500; // степендия
    int percentFree = 100;
    float percentBank = 5; //
    float[] monthPayments = new float[120];
    private TextView countOut;// поле вывада количества месяцев выплаты
    private TextView maneMonthOut;//поле выпески по ежемесячным платежам
    //метод подсчета стоймости с учетом первоночального взноса
    private float priceWithContribution() {
        return telescope-account;//возврат подсчитанного значения
    };
    //
    public float mortgageCosts (float amount, int percent){return (amount * percent) / 100 ;
    }
    public int countMonth(float totel, float mortgageCosts, float percentBankYear) {
        float percentBankMonth = percentBankYear/12;
        int count = 0;//счетчик месяцев выплаты
        //алгоритм расчета
        while (totel>0) {
            count++;
            totel = (totel+(totel*percentBankMonth)/100)-mortgageCosts;
            //заполним масив ежемесячных платяжей
            if (totel>mortgageCosts) {                 //если сумма долга меньше ежемесячного платеда то
                monthPayments[count-1]=mortgageCosts;//добовляем целый платеж
            }else {
                monthPayments[count-1]=totel;//значит что мы добовляем в масив платеж равное остатку долга
            }
        }
        return count;
    };
    //вывод на экран полученных значений
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countOut = findViewById(R.id.countOut);
        maneMonthOut = findViewById(R.id.manyMonthOut);
        countOut.setText("на телескоп можно будет накопить за  " +countMonth(priceWithContribution(), mortgageCosts(stependia,percentFree),percentBank) + " месяцев");
        String monthlyPaymentsList = " ";
        for (float list: monthPayments) {
            if (list > 0) {
                monthlyPaymentsList = monthlyPaymentsList + Float.toString(list) + " монет";
            }else {
                break;
            }
        }
           maneMonthOut.setText("Первоначальный взнос " + account + " монет, ежемесячные выплаты: " + monthlyPaymentsList);
    }
}
