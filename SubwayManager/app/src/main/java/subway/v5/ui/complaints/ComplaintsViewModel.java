package subway.v5.ui.complaints;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComplaintsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ComplaintsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is complaints fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}