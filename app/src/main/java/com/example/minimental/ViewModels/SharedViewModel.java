package com.example.minimental.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.minimental.FifthQuestion;
import com.example.minimental.MissingDetail;
import com.example.minimental.SixthQuestion;
import com.example.minimental.TenthQuestion;
import com.example.minimental.ThirdQuestion;
import com.example.minimental.informationQuestion;
import com.example.minimental.repository.AppRepository;
import com.example.minimental.repository.FireBaseCallBack;
import com.example.minimental.secoundQuestion;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    //region New View-Model For Second Fragment
    private AppRepository repository;
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> userId = new MutableLiveData<>();
    private MutableLiveData<String> dateTimeLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> permission = new MutableLiveData<>();


    public SharedViewModel() {
        repository = new AppRepository();
    }

    public void setUserId(String userId1){

        userId.setValue(userId1);
        repository.setUserId(userId);
    }

    private MutableLiveData<Integer> version = new MutableLiveData<>();

    public LiveData<Integer> getVersion(){

        version = repository.getVersion();
        return version;
    }

    public void setPermission(Boolean permission1){

        permission.setValue(permission1);
        repository.setPermission(permission);
    }

    public  void setDateFirst(String dateTime){
        dateTimeLiveData.setValue(dateTime);
        repository.setDataTimeFirst(dateTimeLiveData);
    }
    public  void setDatelast(String dateTime){
        dateTimeLiveData.setValue(dateTime);
        repository.setDataTimeLast(dateTimeLiveData);
    }

    public void setUserName(String userName){

        username.setValue(userName);
        repository.setUserName(username);

    }

    public void loadData()
    {
        repository.load();
    }

    public void loadId()
    {
        repository.loadId();
    }



    //missing details
    private MutableLiveData<MissingDetail> missingDetailMutableLiveData = new MutableLiveData<>();

    public LiveData<MissingDetail> getMissingDetailMutableLiveData(){

        missingDetailMutableLiveData = repository.getMissingDetail();
        return missingDetailMutableLiveData;
    }

    public void getMissingDetailCallback(FireBaseCallBack callBack){

        repository.loadMissingDetail(callBack);
    }

    public void setMissingDetailMutableLiveData(MissingDetail missingDetail){

        missingDetailMutableLiveData.setValue(missingDetail);
        repository.setMissingDetail(missingDetailMutableLiveData);
    }


    //informationQuestion
    private MutableLiveData<informationQuestion> infoLiveData = new MutableLiveData<>();
    public MutableLiveData<informationQuestion> getInfoLiveData(){

        infoLiveData = repository.getInformationData();
        return infoLiveData;
    }
    public void setInfoLiveData(informationQuestion info){

        infoLiveData.setValue(info);
        repository.setinfo(infoLiveData);
    }

    private MutableLiveData<secoundQuestion> secondQuestionLiveData = new MutableLiveData<>();

    public MutableLiveData<secoundQuestion> getObjectdata(){

        secondQuestionLiveData = repository.get3ObjectData();
        return secondQuestionLiveData;
    }

    public void setObjectData(secoundQuestion secoundQuestion){

        secondQuestionLiveData.setValue(secoundQuestion);
        repository.setObject(secondQuestionLiveData);
    }


    //region Third Question Math Version Data Member and spelling Version Data Member
    private MutableLiveData<ArrayList<String>> mathAnswerGiven = new MutableLiveData<>();
    private MutableLiveData<ArrayList<String>> spelledWord = new MutableLiveData<>();
    private MutableLiveData<ThirdQuestion> loadMathOrSpell = new MutableLiveData<>();

    //region Third Question Math Version Methods
    public void setMathAnswerGiven(ArrayList<String> mathAnswer)
    {
        mathAnswerGiven.setValue(mathAnswer);
        repository.setMathAnswer(mathAnswerGiven);
    }
    public MutableLiveData<ThirdQuestion> getMathAnswerGiven()
    {
        loadMathOrSpell = repository.getMathAnswer();
        return loadMathOrSpell;
    }

    public void setSpelledWord(ArrayList<String> Word)
    {
        spelledWord.setValue(Word);
        repository.setSpellAnswer(spelledWord);
    }

    public MutableLiveData<ThirdQuestion> getSpelledWord()
    {
        loadMathOrSpell = repository.getSpellAnswer();
        return loadMathOrSpell;
    }


    //region Fourth Question Data Members
    private MutableLiveData<secoundQuestion> fourthQuestionLiveData = new MutableLiveData<>();

    public void setFourthQuestionLiveData(secoundQuestion secoundQuestion){

        fourthQuestionLiveData.setValue(secoundQuestion);
        repository.setObjectForthLiveData(fourthQuestionLiveData);
    }



    //region Fifth Question Data Members
    private MutableLiveData<FifthQuestion> PicItemDescription = new MutableLiveData<>();


    public MutableLiveData<FifthQuestion> getFifthQuestionLiveData(){

        PicItemDescription = repository.getPicDescription();
        return PicItemDescription;
    }
    public void setFifthQuestionLiveData(FifthQuestion itemDescription){

        PicItemDescription.setValue(itemDescription);
        repository.setPicDescription(PicItemDescription);
    }

    //region Sixth Question Data member
    private MutableLiveData<SixthQuestion> repeatedSentence = new MutableLiveData<>();

    public MutableLiveData<SixthQuestion> getRepeatedSentence(){

        repeatedSentence = repository.getSentence();
        return repeatedSentence;
    }

    public void setRepeatedSentence(SixthQuestion value)
    {
        repeatedSentence.setValue(value);
        repository.setSentence(repeatedSentence);
    }
    //region Sevnth Question Data Member
    private MutableLiveData<Boolean> currectOrderSeventh = new MutableLiveData<>();



    public void setCurrectOrderSeventh(Boolean value)
    {
        currectOrderSeventh.setValue(value);
        repository.setCurrectPicOrder(currectOrderSeventh);
    }

    //region Eight Question Data Member
    private MutableLiveData<Boolean> currectOrderEighth = new MutableLiveData<>();


    public void setCurrectPicOrderEighth(Boolean value)
    {
        currectOrderEighth.setValue(value);
        repository.setCurrectPicOrderEighth(currectOrderEighth);
    }

    //region Ninth Question Data Member
    private MutableLiveData<SixthQuestion> sentenceForNinth = new MutableLiveData<>();



    public void setSentenceNinthQuestion(SixthQuestion sentenceNinthQuestion)
    {
        sentenceForNinth.setValue(sentenceNinthQuestion);
        repository.setSentenceForNinth(sentenceForNinth);
    }

    //region Tenth Question
    private MutableLiveData<TenthQuestion> picForTenth = new MutableLiveData<>();

    public MutableLiveData<TenthQuestion> getpicForTenthQuestion(){

        picForTenth = repository.getpicForTenth();
        return picForTenth;
    }

    public void setpicForTenthQuestion(TenthQuestion sentencepicForTenth)
    {
        picForTenth.setValue(sentencepicForTenth);
        repository.setpicForTenth(picForTenth);
    }

}
