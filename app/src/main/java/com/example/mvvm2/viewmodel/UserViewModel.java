package com.example.mvvm2.viewmodel;

import android.content.Context;
import android.os.Handler;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm2.model.User;
import com.example.mvvm2.view.UserAdapter;

import java.util.ArrayList;

public class UserViewModel extends BaseObservable {


    private String name;
    private String tell;
    private Context context;

    private MutableLiveData<ArrayList<UserViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private ArrayList<UserViewModel> arrayList = new ArrayList<>();


    public UserViewModel(Context context) {
        this.context = context;


//        /// connect to server
//
//        UsersRepository usersRepository = new UsersRepository();
//        usersRepository.getUsers();
//        usersRepository.getArrayListMutableLiveData().observe((LifecycleOwner) context, users -> {
//
//            for (int i=0; i<users.size() ; i++){
//                UserViewModel userViewModel = new UserViewModel(users.get(i));
//                arrayList.add(userViewModel);
//            }
//            notifyPropertyChanged(BR.arrayList);
//
//        });


//        new Handler().postDelayed(() -> {


        for (int i = 0; i < 20; i++) {
            User user = new User("mahdi: " + i, "09362222222");
            UserViewModel userViewModel = new UserViewModel(user);
            arrayList.add(userViewModel);
        }

        notifyPropertyChanged(BR.arrayList);

//        },3000);


//        mutableLiveData.setValue(arrayList);


    }

    public UserViewModel(User user) {
        this.name = user.getName();
        this.tell = user.getTell();
    }


    @BindingAdapter("bind.recyclerUser")
    public static void recyclerViewBinder(final RecyclerView recyclerView,
                                          final MutableLiveData<ArrayList<UserViewModel>> arrayListMutableLiveData) {

        arrayListMutableLiveData.observe((LifecycleOwner) recyclerView.getContext(), userViewModels -> {
            UserAdapter userAdapter = new UserAdapter(userViewModels);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                    LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(userAdapter);
        });
    }


    @BindingAdapter("bind.recyclerUser")
    public static void recyclerViewBinder(final RecyclerView recyclerView,
                                          final ArrayList<UserViewModel> userViewModelArrayList) {
        UserAdapter userAdapter = new UserAdapter(userViewModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(userAdapter);
    }


    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getTell() {
        return tell;
    }


    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setTell(String tell) {
        this.tell = tell;
        notifyPropertyChanged(BR.tell);
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Bindable
    public MutableLiveData<ArrayList<UserViewModel>> getMutableLiveData() {
        return arrayListMutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<ArrayList<UserViewModel>> mutableLiveData) {
        this.arrayListMutableLiveData = mutableLiveData;
    }

    @Bindable
    public ArrayList<UserViewModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<UserViewModel> arrayList) {
        this.arrayList = arrayList;
        notifyPropertyChanged(BR.arrayList);
    }


}
