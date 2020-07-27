package introduction.android.menusDemo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;

public class MenusDemoActivity extends Activity {
    private TextView textview;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textview=(TextView)findViewById(R.id.textview1);
        registerForContextMenu(textview);       
    }
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	switch(item.getItemId()){
    	case 1:
    		Log.i("menu","submenu item 1 selected");
    		break;
    	case R.id.item1:
    		textview.setText("item1 selected!");
    		break;
    	case R.id.item2:
    		textview.setText("item2 selected!");
    		break;
    	case R.id.item3:
    		textview.setText("item3 selected!");
    		break;    		
    	default:
    		Log.i("menu","other items selected");
    		break;	
    	}    	
		return super.onOptionsItemSelected(item);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        SubMenu submenu=menu.addSubMenu("子菜单");
        submenu.setIcon(android.R.drawable.ic_menu_crop);
        submenu.add(0,1,0,"子菜单项一");
        submenu.add(0, 2, 0, "子菜单项二");
        return true;
    }
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.item6:
			Log.i("menu","item6!");
			break;
		case R.id.item7:
			Log.i("menu","item7!");
			break;
		default:
			break;			
		}
		return super.onContextItemSelected(item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.add(0, R.id.item6, 0, "上下文菜单项一");
		menu.add(0, R.id.item7, 0, "上下文菜单项二");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
}