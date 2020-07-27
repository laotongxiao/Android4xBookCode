package introduction.android.mydbDemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class MyDbProvider extends ContentProvider {
	
	private dbHelper mydbHelper;
	
	private static final UriMatcher myUriMatcher;
	static {
		myUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		myUriMatcher.addURI(MyFriendsDB.AUTHORITY, "friends", MyFriendsDB.FRIENDS);
		myUriMatcher.addURI(MyFriendsDB.AUTHORITY, "friends/#", MyFriendsDB.FRIENDS_ID);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		if (myUriMatcher.match(uri) != MyFriendsDB.FRIENDS_ID) {
			throw new IllegalArgumentException("Wrong Insert Type： " + uri);
		}	
		String id = uri.getPathSegments().get(1);
		if (selection==null)
			selection=MyFriendsDB.ID+"="+ id;
	    else
		    selection=  MyFriendsDB.ID+"="+ id+ " and "+ selection; 		
		SQLiteDatabase db = mydbHelper.getWritableDatabase();		
		int i=db.delete(dbHelper.TB_NAME, selection, selectionArgs);
		if(i>0)
			Log.i("myDbDemo","数据更新成功！");
		else
			Log.i("myDbDemo","数据未更新！");
		return i;		
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (myUriMatcher.match(uri)) {
		case MyFriendsDB.FRIENDS:
			return MyFriendsDB.CONTENT_TYPE;

		case MyFriendsDB.FRIENDS_ID:
			return MyFriendsDB.CONTENT_ITEM_TYPE;

		default:
			throw new IllegalArgumentException("Unknown URI get type: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		if (myUriMatcher.match(uri) != MyFriendsDB.FRIENDS) {
			throw new IllegalArgumentException("Wrong Insert Type： " + uri);
		}
		
		if (values == null) {
			throw new IllegalArgumentException("Wrong Data.");
		} 
		
		SQLiteDatabase db = mydbHelper.getWritableDatabase();
		long rowId = db.insert(MyFriendsDB.TABLE_NAME, null, values);
		if (rowId > 0) {
			Uri insertUri = ContentUris.withAppendedId(MyFriendsDB.CONTENT_URI, rowId);
			return insertUri;
		}		
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mydbHelper=new dbHelper(getContext(),MyFriendsDB.DATABASE_NAME,null,MyFriendsDB.DATABASE_VERSION);
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub		

		switch (myUriMatcher.match(uri)) {
		case MyFriendsDB.FRIENDS:			
			break;

		case MyFriendsDB.FRIENDS_ID:
			Log.d("MyDbProvider","select id");
			String id = uri.getPathSegments().get(1);
			if (selection==null)
					selection=MyFriendsDB.ID+"="+ id;
			else
				selection=  MyFriendsDB.ID+"="+ id+ " and "+ selection;            
			break;

		default:
			throw new IllegalArgumentException("Unknown URI type: " + uri);
		}
		if(sortOrder==null)
			sortOrder="_id ASC";
		SQLiteDatabase db = mydbHelper.getReadableDatabase();
		Cursor c =db.query(MyFriendsDB.TABLE_NAME, projection, selection, selectionArgs, null,
				null, sortOrder);
		Log.d("MyDbProvider",""+c.getCount());	
		
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		if (myUriMatcher.match(uri) != MyFriendsDB.FRIENDS_ID) {
			throw new IllegalArgumentException("Wrong Insert Type： " + uri);
		}
		
		if (values == null) {
			throw new IllegalArgumentException("Wrong Data.");
		} 
		String id = uri.getPathSegments().get(1);
		if (selection==null)
			selection=MyFriendsDB.ID+"="+ id;
	    else
		    selection=  MyFriendsDB.ID+"="+ id+ " and "+ selection; 
		SQLiteDatabase db = mydbHelper.getWritableDatabase();		
		int i=db.update(dbHelper.TB_NAME, values, selection, selectionArgs);
		if(i>0)
			Log.i("myDbDemo","数据更新成功！");
		else
			Log.i("myDbDemo","数据未更新！");
		return i;
	}

}
