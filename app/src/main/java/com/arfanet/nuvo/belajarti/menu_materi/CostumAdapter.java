package com.arfanet.nuvo.belajarti.menu_materi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arfanet.nuvo.belajarti.R;

import java.util.ArrayList;

/**
 * Created by Nuvo on 28/11/2017.
 */

public class CostumAdapter extends BaseAdapter {

    Context c;
    ArrayList<PDFDoc> pdfDocs;

    public CostumAdapter(Context c, ArrayList<PDFDoc> pdfDocs){
        this.c =c;
        this.pdfDocs = pdfDocs;
    }

    @Override
    public int getCount() {
        return pdfDocs.size();
    }

    @Override
    public Object getItem(int i) {
        return pdfDocs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null)
        {
            //INFATE COSTUM LAYOUT
            view= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        final PDFDoc pdfDoc = (PDFDoc) this.getItem(i);

        TextView nameTxt = (TextView) view.findViewById(R.id.nameTxt);
        ImageView img    = (ImageView) view.findViewById(R.id.pdfImage);

        //BIND DATA
        nameTxt.setText(pdfDoc.getName());
        img.setImageResource(R.drawable.pdf);

        //VIEW ITEM CLICK
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPDFView(pdfDoc.getPath());

            }
        });

        return view;
    }

    //OPEN PDF VIEW
    private void openPDFView(String path)
    {
        Intent i=new Intent(c, PDFActivity.class);
        i.putExtra("PATH", path);
        c.startActivity(i);
    }

}
