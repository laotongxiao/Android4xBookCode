package introduction.android.mydbDemo;

import android.net.Uri;

public class MyFriendsDB {
	public static final String AUTHORITY = "introduction.android.mydbdemo.myfriendsdb";
	
	public static final String DATABASE_NAME = "mydb";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "friends";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/friends");
	
	public static final int FRIENDS = 1;
	public static final int FRIENDS_ID = 2;
	
	public static final String CONTENT_TYPE="vnd.android.cursor.dir/mydb.friends.all";
	public static final String CONTENT_ITEM_TYPE="vnd.android.cursor.dir/mydb.friends.item";
	
	
	 public static final String ID = "_id";
	 public static final String NAME = "name";
	 public static final String AGE = "age";
}
