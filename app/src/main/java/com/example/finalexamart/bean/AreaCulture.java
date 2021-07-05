package com.example.finalexamart.bean;
import java.util.ArrayList;
import java.util.List;
//西南 西藏 云南 贵州 四川 重庆4
//西北 新疆 陕西 甘肃 青海 宁夏5
//东北 黑龙江 吉林 辽宁3
//东南 中南 浙江 台湾 广东 河北 湖南2
// 中华民族0
//其他 书法1
//public class AreaCulture implements Parcelable {
public class AreaCulture{
    private String id;
    private String title;
    private String content;
    private String background;
    private List<String> photos=new ArrayList<>();
    public AreaCulture(String id,String title,String content,String background,List<String> photos){
        this.id=id;
        this.title=title;
        this.content=content;
        this.background=background;
        this.photos=photos;
    }
//    protected AreaCulture(Parcel in) {
//        id=in.readString();
//        title = in.readString();
//        content = in.readString();
//        background=in.readString();
//        photos=in.readStringList();
//        //background = in.readParcelable(String.class.getClassLoader());
//        //photos = in.createTypedArrayList(String.CREATOR);
//    }

//    public static final Creator<AreaCulture> CREATOR = new Creator<AreaCulture>() {
//        @Override
//        public AreaCulture createFromParcel(Parcel in) {
//            return new AreaCulture(in);
//        }
//
//        @Override
//        public AreaCulture[] newArray(int size) {
//            return new AreaCulture[size];
//        }
//    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
    public List<String> getHotPhotos() {
        //TODO 计算热度前4的图片
        return photos;
    }

    public void setHotPhotos(List<String> photos) {
        //TODO 计算热度前4的图片
        this.photos = photos;
    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.id);
//        dest.writeString(this.title);
//        dest.writeString(this.content);
//        dest.writeString(this.background);
//        dest.writeStringList(this.photos);
////        dest.writeParcelable(background,flags);
////        dest.writeTypedList(photos);
//    }
}
