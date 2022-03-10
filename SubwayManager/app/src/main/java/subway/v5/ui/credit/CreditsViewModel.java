package subway.v5.ui.credit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreditsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CreditsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is credits fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}