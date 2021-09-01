package com.example.feesstructure;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config1 {

    private Context mContext;
    private studentAdapter mStudentAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<student> students,List<String> Keys){
        mContext=context;
        mStudentAdapter=new studentAdapter(students,Keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mStudentAdapter);

    }

    class StudentItemView extends RecyclerView.ViewHolder{
        private TextView mReffid;
        private TextView mname;
        private String key;

       public StudentItemView(ViewGroup parent) {
           super(LayoutInflater.from(mContext).
                   inflate(R.layout.student_view, parent, false));

           mReffid = (TextView) itemView.findViewById(R.id.studentReff);
           mname = (TextView) itemView.findViewById(R.id.studentName);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(mContext,FeesView.class);
                   intent.putExtra("key",key);
                   intent.putExtra("rfid",mReffid.getText().toString());
                   intent.putExtra("name",mname.getText().toString());
                   mContext.startActivity(intent);
               }
           });
       }
       public void bind(student student,String key)
       {
           mReffid.setText(student.getRfid());
           mname.setText(student.getName());
           this.key=key;
       }
    }

    class studentAdapter extends RecyclerView.Adapter<StudentItemView>{
        private List<student> mStudent;
        private List<String>  mKeys;


        public studentAdapter(List<student> mStudent, List<String> mKeys) {
            this.mStudent = mStudent;
            this.mKeys = mKeys;
        }

        @Override
        public StudentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StudentItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentItemView holder, int position) {
             holder.bind(mStudent.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mStudent.size();
        }


    }

}
