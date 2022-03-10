package subway.v5.ui.laf;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LafViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LafViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is lost and find fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}