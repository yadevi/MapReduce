package practice;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser; 

public class NoMapRedDef  {

	public static void main(String args[])throws Exception{
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		if (args.length<2){
			System.out.println("input right parameters");
			System.exit(-1);
		}
		
		Job job = Job.getInstance(conf, "word count");
		//or Job job=Job.getInstance(getConf());
		job.getConfiguration().set("fs.file.impl", "WinLocalFileSystem");
		job.setJarByClass(DefMapRed.class); 
		
		/*String[] files=new GenericOptionsParser(conf,arg0).getRemainingArgs();
		FileInputFormat.setInputPaths(job, new Path(files[0]));
		FileOutputFormat.setOutputPath(job, new Path(files[1]));*/
		
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		for (int i = 0; i < otherArgs.length - 1; ++i) {
		      FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
		}
		FileOutputFormat.setOutputPath(job,new Path(otherArgs[otherArgs.length - 1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);		
	}
}
