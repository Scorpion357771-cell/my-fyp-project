package com.fyp.diseasedetector.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.fyp.diseasedetector.HelperClasses.HomeAdapter.BestDoctorAdapter;
import com.fyp.diseasedetector.HelperClasses.HomeAdapter.BestDoctorHelperClass;
import com.fyp.diseasedetector.HelperClasses.HomeAdapter.CategoryAdapter;
import com.fyp.diseasedetector.HelperClasses.HomeAdapter.CategoryHelperClass;
import com.fyp.diseasedetector.HelperClasses.HomeAdapter.NearbyAdapter;
import com.fyp.diseasedetector.HelperClasses.HomeAdapter.NearbyHelperClass;
import com.fyp.diseasedetector.LoginActivity;
import com.fyp.diseasedetector.LoginSelection;
import com.fyp.diseasedetector.R;
import com.fyp.diseasedetector.UserPorfile;
import com.fyp.diseasedetector.doctorprofile;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class PatientDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    RecyclerView bestDoctorRecycler;
//    RecyclerView nearbyRecycler;
//    RecyclerView categoryRecycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;
    LinearLayout contentView;

    static final float END_SCALE = 0.7f;


    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private MenuItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patient_dashboard);


        //Hooks
//        bestDoctorRecycler = findViewById(R.id.best_doctor_recycler);
//        nearbyRecycler = findViewById(R.id.most_viewed_recycler);
//        categoryRecycler = findViewById(R.id.category_recycler);
        menuIcon = findViewById(R.id.menuicon);
        contentView = findViewById(R.id.content);



        //Image Slider

        ImageSlider imageSlider = findViewById(R.id.img_slider);

        List<SlideModel> slideModelList = new ArrayList<>();
        slideModelList.add(new SlideModel("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUSERIWFRUXFxgYFxUYGBcYFxceGBcYGBcXFhgYHikhGBsmHhUYIjIjJiosLy8vFyA0OTQtOCkuLywBCgoKDg0OHBAQGy4mICYuLi4uLjkuLjAzMy4uLi4wLjA4LDAuLjAuMy4uLi4uLiwuLi42Li4uLi42Li4uLi4wLv/AABEIAHsBmgMBIgACEQEDEQH/xAAbAAABBQEBAAAAAAAAAAAAAAAFAAIDBAYBB//EAEQQAAIABAMFBAYHBgUEAwAAAAECAAMRIQQSMQVBUWFxBhMigTJCUpGhsRQjM3LB0fBDU2KC4fEHY3OSohY0ssIkg9L/xAAaAQACAwEBAAAAAAAAAAAAAAAAAQMEBQIG/8QALBEAAgICAgIBAgUEAwAAAAAAAAECAxEhBDESUUETIgVhgZGhMtHh8CNxsf/aAAwDAQACEQMRAD8Al76O99ADv24x0YhuMbWTzWA73kdWbAP6U3GJsLiGLXgyGA+kyJlaKkoxewWGaYwWWpY8B8zwEDxjYJNvCOVi5hMDNmfZy2bnS3vNo0uyezSJRp1Hb2fVH/687QfHDdwinZyktRNGrgt7m8GHOwMSL91/yX84oT5LIaOpU8CCI9HiOfIV1yzFDLwP4cIjjyn8ollwI4+1nm9YYxjS7U7LEVaQaj2G1/lO/wA4zM6WykqwII1BsRFyucZ9Mzrap1vEkMJjlI6FMdAiUhOAw9WjhEKEMeGjuaIqGOwsDyPzQ1jHBHaw8AcWETD1hpEAD0hwjgEdjkY4QoQhRyMRhCFCEAHDHIekssQqgknQC5jR7L7N6NP/ANg/9iPkI5nZGC2SV0zseImP2ls+bOl5ZMtnaosor7zoII7P7N4pUGaSQaC2ZK/Ax6FLQKKKAANwsPcIdFZ8uXwi/HgQxts86n4Z5Zo6MvUU93GIjHpMxAwysAwO4iojIY3Z+Hea0vDT071dZJYV5hTxG8bt9I7r5Cl2Q3cNx3F5AkKJJ8lkbK6lSNxiF29+6LK2UWsdiLHcK+dIF7QJebLl008ZFfIboKqKCkAGxZWc70qa0pXSlokhByekcykl2FZ8ylBQU6/0iZWPCK+DbMA9PSueXIRJ3lDTcNOn9Pyga+AXsmDcoGbfl1QNvU/P+tIKiB23bS24EU/KOUMsbNm5pYMWhAvYTeADlWCkJjFChQoQChQoUACrHY5WEIAHxykKkKkIZkfoHOF9B5wY7qOGVEuCPIH+hHjEmGwpDVrBzAbLmTmyylqd53Dmx3RtNj9nZGHKmayvNPo5iAKi9JanUjj8ohstjX32WKePO3rr2A9hdmJk2jTKy0/5N0B0HM/GNvgcFLlLllqFHxPMnfHcdjZclDMnTEloNXdgqiulSbRODGfZdKfZr08eFS137FHY5Azbe3pOGH1jVYiqy1u7eW4czQRGk28IllJRWZPCCZNLmMxtDtWrMJeHZaE0bEuCZKdKemedQvM6Rl9ubfmT6F2Uyz+wQmg3/WGnjPw5AwIxE9nIzUVbW0AHtEC+/h0i7VxPmZl3/iHxX+/+9BXbG0lSaHw0+c80enPZhlfkq0pl5UpwEGpG3knKFx8kqdBPUG3DMBdPOx4CMjiJaplaVMzEHWwNRcEDUL14c4LbD2JicUPD9VKJq0yhAfjRa+PyosWJ11qKzrHz8lOq+2U2ks5+O0GMdsFlXvJJE6WRUMtzToNeogRHoGyNgS8NLySWYHUsTXMea+j7gIr7V2PLmVMxe7b96von743efvMVY8lJ4e17L8+E2sx0/X+TDVhtYJ7U2PNkXYVX2xcefDzgYYuxkpLKM+cJReJIdHAY4sOAhnIoVI7ChDOoIVLwljo1hAOhQoUIZ1Y7HBEuHkM7ZUUseA/HgIT0NJvSI4I7M2PMnX9FPaO/7o3wZ2X2eVaNNozez6o6+18olxG2fo6zHxvdSk7zLIyO0x5o9UBMgPeG3gXNr5mrZyPiJoU8L5n+xe2fs6XJFEF97H0j5/hFuBGx8ViprmZNlLIk0okpvFiGuKPMIOWXavg8RvcjSC8VG29s0VFRWEKKm0tpSpC55zhQTRRcsx9lFF2PIRm9v9tkSqYXLNfQzDeUvSn2h6EDnakYjEY95hZpp7x2tna7KOCUoEHICkWaeLKe3pFHk8+ur7Vtml232mmOQCDKk6mUrUnTVvZ5i17sHgvPxHQZ/am0+9KqstJUuXaXLQAZeZbUnnFBiTc3gnsXYU7EtSUtFHpTGsg8955D4RfVVdSyzIlfdfLxW8/Bf2d2neglYlDiE0B/bL91vXPI35wbxWwW9OSSwpXu2BWaoPFTr15Qf2B2Zk4YVAzzN8xhf+UeqPjzMGJshW1Gmh0I6EXHlFCy+Kl/xrRrU8SThi15f8r9TxzbjspUVI1qKkcNYENHsG2Ngy515yd5TSYoAmL94CzjpfkdY8+2x2WnyiXlgTZdTR09W+jqbqRGlxeXXJeL0zO5XCsg/JbQFwE1u8QZjSulTT3RexeIv0/VIu4LBKFUlAHprvrArHp4jE0rIuWfRBCDSDWEm5lEdxkkOhU7/wBCKGypu6CU70T0MVn2Ssr4HChNOA+VT8YtxDhtB90Q93pCYD4RisZ5h6zaiDAExhoNYYLnl+rRLAAqQqwoUIBwjsIQoQyjkjQbI7LM9GnVRfZ9Y9fZHx6Qf2TsOXJofSf2ju+6N3zgrFa3lN6h+5o0cFLc/wBiLC4ZJahJahVG4fM8TAjtDNo8nu8F9JxALGSzKBLk6BneewPdDSy1Y0sDSJ9sYLFTJkhsPihIlo1Z0sylmd6tR4QxNUsCLe1XdBWKXZopY0jP7O7OEzFxOOmfScQt0sVkSf8AQlEkA/xtVjxGkHp05UUu7BVAqWJAAHEk6QG292nk4aqn6ybulqbj750QfHgDGI2vtx5pzO+ZgfCq/YrrcKR4jelTU60K6RPVx5z38FTkcyuvWcv0HO0XbJgMuFUgGv1zDXce7U897e7fGJYs7FiSxN2ZjUnmzH8YsEO7fWltCbkk9BwuYbMmBagVpQgrUUF9SRv/AFWL1aUPtrWX8v4/cx75zt+6x4Xwv8DQqrQkktZqWpxAYHy3w/DSJs9+7lIWY3yr82J+ZMHez/Y2bPo82sqXrcfWN91T6I5n3GPQ9l4CRIHdSVVeIrVjzY6k33xHZbCt5z5S/hE1HEnatrxj/LM32f7DJLo+JpMfUJ+zXr7Z625RslFLCIu7bNmznLQ+EhaVqKGoFbUPviTPXTTj+XGKNlkpvMmbFVEKliKHM1IblJ104fnClgai/PjEkcEpSbC09CgHsH0Dyp6vla+hgBtLs9Lc+D6mYfVPoN90/l5gRq4ZMlhhRgCOBuI6hOUHlM4srjNYkjzHGYKZJbLMUqdx3Hod8V49JxGEOUrQTU3o9z/Kx1PX3iM3j+zgapw5uNZTWYdCfx14xdr5KepaMy7hSjuG0ZxYTw6ZKZWyspVhqCKGGOItFHockIRxIdWEMdHIUqWzMFUVJNAOsbHZWwUlUZ/G/PQdB+MRWWxh2T00SteuvYF2XsF5lGfwJ/yPQbupjV4PCJLXKi0G/iep3xNCihZbKfZrVUQr679gzZPaLDYmbOkyJoeZIbLNWjDKakUqQAbqRbhHcHsKUk5sQxabOYmkyYQzS1J+zlAACWg0sKmlyYIOyqGYkKBdmNALb2P4mMdtrtjVXGDK21muQK/6Ms3f7zCnANChCU3iJ1bbCtZkzR7X2zKw4BmEljXLLUZpj09leHM0A3mMNt7b7zvDNtLOkiW9iP8AOcCrn+EUW/EVjPZ3dy5LO5NS1SXPnrb4CLK4QIM06orou86Vrw15aGNGvixhuW2Yt/4hO3KgsIgk4Vn0FBxNlHn5H3GLRlpXu5SGa7UAIqaH+ELqfy6wQ2RsCdizVF7qRUHMa5bClVX1m5ig5waxe3dnbJAW7zCLsBmdhyIFAOVhzjq3kKOvn1/dnHH4crNvS9/2R3s/2F0mYrqJQP8A5sPkPfG6kylVQqKFUCgAFAOQAjEbG/xNwOIOU4gySTQCZKyDzbMyjzIjWtOfKHV0dTcW16MDT4RnWWTsf3M2aqa6I4ii/HYpSMcpOUjKx0B0NPZO/pryi7EbTXZOmn0KKs3CgnMCVb2hqeTDRh10rakWoUIZltq7FVqlgJbfvVH1Z5uuqb76c90YztDsiZJoXXw6BhdTwoY9bgfiMACCFoAfSlkZpbV1qvq77ilzU1ievkShrtFW3iQntaZ41JcqawVkz6gg8I0G2OyCE/U/UzDpLY1luf8ALf32N7aARk8Vh5sp+7moUbgd/Q6EcxF2u2MujOtolDtBNCAPIfCKU2dUkxLLWo5RBiREpXOK1usStMyj3RUlPevCK8zEF2oNB+iYMjwGcPigbUpFqAyHdBWS1RCYiSFHIUADwYVYbHaxyM9HhQoAbZ7TrKDrJXvnSmcj7OWSaDvX0Br6ov0jLjFyeEb85xgsyYZxmLlykLzXCKN5PuA4k8Bcxi9t9ri6ukp2kCliVPeTLVFDpKB9/wB2AG0NrNNbOzM8yvhJsqA+qiaAnz0FSbwZlbOplSZewnT3I1FfBKU8CQaj84nsUOPHynt+jOfIs5GY1aXWfzZkkFTSup1J47yYvLLWXRq1NxfpuFf1UwW7V4kt3cnLWZXMQouM3ooANTQ6dII7C7Du5EzFkqNe7B8R+83qjkL8xEyt+pBSnmKfx7KkaJQscIJSkn/V8IzeAwk7FP3cla01NaBQd7Hh8+cb3YvZeRhQJk36yZuYgkKQCfAu7Q3Pwg7Kwayky4eWi0oAKUBA3VF/O8PlS2Aq71GtCBv3HXjxPutEFl7a8YLCNGjhRg/Ox+UvYwlpiHL4DcG9R1DU1oagjfrpEsmQqVAqSTW9zw8hS0OJCruRFGtgAB8FEUpuODN3UokOfXyZlU1PpAkGtEN9Lqd4rXLxcnzlUVmMBy/pv0+EDMRLmYoKVbu5RpmVk8ZF63zFSCDpSoIBruhpwaqe9xbBnJoFXMUHiqoUHxXygkVoTxoKXXkvNpnJloRQy7VNzqwOhBFuUAC2cJalklktU52OoqQAKbhYDThXUkkjEUuWFFAKfrU8TEUzGKDlXxNwWhpzN7DrABO7AAk2AuYq52mCqHKp3kXPCg4e49Ickk1zO9TwHhUa3p57zCmYwA0FTz/LjAA5FSUKDea8SSdTA6bjVmPltVa6VzLpXxDQ6W+cSYjCd4DQlgdSDQ+cWMLgVQXvy/Otz5wAUsVhRMX61DMXc4FJi86DXqv+2M5tHs+wGeSe9Tl6Q8hr5e6N5SKs7CgnMpyN7Q3/AHho3DjwIiWu2UOiC3jws779nmawi4G+NrtPZ0t7z1yN++X0T9/2f5rbgxjMbV2HNknMwzJ7a6fzD1YvV3xn3pmXdxZ17W0TdmCDPrrlFffaNyxr4h/aPLpWNaS4mAaajiI3OzNqy5iI6PQtW26xv0ilyU1Y2+jT4TjKpJdrsLQK2xt+XIPdgGbOPoyUu99C3sLzPkDFrFVmIyBzKYizqAadK+6ovfcbx5/tnYeIw6lQKoxOaYtS0yp/aPq2ulq8DSsKmEZyw3gOVbOqOYxyVNr7bmYg1ntUD0ZK/ZKa2LfvCBS531oFgXPnlzVulgAAOAA0hTZVADmUig0IqCRUgitRQ1HlB7s/2SnYijvWVK9ojxN9xT8zbrGt/wAdUc9I88/rcieO2BMDImO4WSrM50C6868uNbRvtg9iVU97iyJjm+T1R94+ueWnWNJsnZEnDLlkpTidWbmx3wRjPu5blqOl/Jr8b8PjXue3/AO2v4ZVFsCUW1qAsARyqPD5x57trYolT5s+fgxipEyjZwod5BAofBWpTT0dwEenYiQHUqwqDr/TgecDTKdDQgsNzqKn+ZRcHpUdNIrpKUcMutyhLyR5zs7Y2Dm+OScOzWBlplVBaozo1HVutNdBeLGyMHNwU8y0dhhZik90WLLLmAFqoW0Q097dKbN8Fh3NSsoGtSSqZq63BF+YiI4SWWIqqsR6qiYpK18ShfEjaVHIUJuY4hU4yzkmlfGyGGiH6eAimYpCv6ExfGK0LKQF8VRSulLRp8JMLIjEULKCRwqAaQGk7Nqio6hZSEEHQkDkfQGoNb0qKCtYt47amRjLRSz0JpQ7hWgAu27Sw3kRJY18ENccFvF41JQJdgKCtNTQUqQBcgViDZ+NaYbyyopc3sa+jWlGPNSdCDQ6tXDHLmnsGoa3C5UvWxoCLWPEDdUxI09plRKoBpnI/wDEb9fhEZKX4URpYAE157z7okgAjmSwwIYAg6gioPUGBeP2UHXKVE1P3bk5hzlzNQdbHkKqILwoM4E1ns8/xPZ2lfo5LUu0prTV/BxuqLGliYyG0J2UsCCGBoQbEdRHs+IwyuBmFaaG4YHirC6npALbewEnCk9O9ppNQBZ6jmBaYNdBw8JN4tV8prUilbwovcNHkmJxNsq79YfhFpeDm1eyMxCZssidK0zIDVaah01Ujf8AGkBZ3hFIuRkpLKM+cJQeGizhTUxeSedAbQI72hCL5n8IvSLax0jhoLI0OiKSdImgZyKsdrDCwjmflBgDXdqcNinUHDkNLH2ksEo78s4NQKbhQ9dI87xEyZMdZJASjBFlUyKhYgejSx0qaX1j0bZe2ZU0BpUwXtTnQmhBupoCaaW3xLtTZUjFCk5KPSzizDod45XHKM/j8hQ00a/M4Mrdxl/Y8peWy0zKyndUEH4xpNj4fHYwAK5WUDeYRQW4b2PT3iDGzuxcqVMLYhjMSvgtRKf5lDX/ANeOtI28tQAAoAAFgNKbqU3RNyORXPqKb9tFTicGyGXJtL0n2C9i7AlSKsKvMPpTXu589w5CCs2UGGU6W5aGo+IhxNIaTapsP1qYpNtvLNWEIwWEsISmlhUnn+JjhU66turYfjTrePPNv/4t4bDzTKlSHnZbFgVVf5dSetIJbB/xMwWIoHz4djp3wohPAOCR76QjoOSnnMW+kCWkoAAhgpViSa0Oc+EUAqaVzaLlvMjnKJeHUBVAAdq5QBai1uaCvS0Tz5qHwTAGBvxHI/1iabLJplbLxoAawpZis4yEWn8ldAFuKzCTTN4SVrur7Nyabq8ImVctTMcG9qgCnKIswGZZKjMD4rUA/Pd74klYUZs7XfjcgclroIjUG3mT/T4Os+hhEyZa8ta39o9CNB+us9US1hXyrzNIq4vHEA5QbcBfyED8LLmzGDGqj2LE9Wbpu1iU5LuOZyCK5eB1HXdWIsFsy+d/SOrbyOAG4frfBLDycopWvy8o7NnKt3YKCaCpAFeF98ADpaACiiKOO2kJfhRS7ncKkDhnKglRY7ibaRXm/SZtFX6kescubLr4QSfHWo0AApqdIu4LAJKFEXzNSb0sCdBYW89YAJ8O7FQXXK16rXNS9rjlE0KGTHCgljQDUwAPig+Dy3kkL/AfQPID1PK1ySDDpWJZzVF8G8tUVuR4fd/aLM2aFFSYAMptTYEqaaAdzNOi+o/3dx03X3kCMftTY83DNW6GtQRdW/A/OPRcTjgzd2VqDcjLmFLXattSNNIXcOUIy97L3y3NSfuM2vRt59IRNG14xLaK8uOs+UHhmJ2P2vKeDEqQPbFWXzGo6j3HWNhs3aKzUDIaqyglG1oRoQYCbR7Ly5mb6OaMPSkvYjpXdY0rY7jGWMqdhXotUI1RqgeXD5QnUpbg/wBDqPJcftuX6/BvG2FIM0TklIXH7N65SdxB9UjdUEWsBrBzD4tWOW6vqUazU4jcwuLgkRjtibeWZ4e8Peb5cw3rv7twNOVxyEaBMQrjI4rS+U2df4lI0p7Sm3KInJ9SJ1VFLyhjD9B2FAyVNddD3qcLCYv4TB7jb1jFzD4lXFVNdxFwQeDA3U8jAInhQoUAHKRWxuKWUudq0GtATTmaaAbzEmIRipCtlNr0rvv8LRVwOFdSzTHJLH0cxZQAABcgc9ABe4JvABF9ZNIDIvdHKxqSSdWBRgdzBCKgGx5RJLky5JoiguxqbjNSoqeg4AfiY7nmPZVMsaEsBmtWoAuOF9LGm4w+XLRDlrVia3NWJpSt+VRCcku2NLI1EY+KaQBuS2WlqZq6msKdNYKO5AYabqDdanDhCQOxKzFXLyJv1/L5wK2x2gkYQZAM8zdLXXkXPq/PgDEMVO7UE0n+jz+vwKc4VLymwzOdEBmTCEAFWJNAOpjF7a7YvMzS8GMqj0p7W/2g6dTflvjP7Y2nNnkPiWtXwSFsBci43G2pqdRbSC+xeyU2flbE1lShdZQsx6j1ep8XSNSvjwqWZmRZyrL5eNSf+/8AhU7MbTxpn5ZTtPWoz5yzIBW5zNdLVpTXgY9SEVMFgpclBLlIFUaAfM8TzMWaf2ivdNTllLBe41Mq4YlLJ2sdAiOU5IuKHh+PSH1iIslPEYNWOcEo+mdaBiBuYGzDWzA0qaUjK9ouzUqZ4pwEl/38sfVt/qyyapvvUji26Ng00XCEFv174F4pXaozFW40v/SOoycXlHMoRksSR5fj+z87CsXmqCnqzFOZGG6h3E84oCcSQBrHruG2WwU5GAB9JHGaW9dap6u+4pXU5oze0OykovWWPo8wn7MmsmYeEqZu+6aG3ogRbr5K6kZ9vDa3Df5AXCKVQV1iS5ixicK0pssxCp5/gdDEVYtJ+jPaaeGcVYdSOCO0gEBWwuVhMSzDQ/D5GNDsjtPMqJU0opqKM4JQ8qgihO6vxgZEUySDuiO3jRnvplrj86yrXa9HpGGx1KA2ruJqD91t55GhizLFPszkO9D6B8vVOulNb1jzHA7QnSLKc8vfLa4pwB3fEcoNbE267TCquGBYkSZngmItB9nMFQ4Br4TupcRnWVTr76Neq6q7+l4fo3cmeK0cFXNr6H7raHpY2rSO7RUleVbxRk4sMCrDMKXRh4gOY3jmKiLEtmHoHOvsMfEPusdd9jXqI5jLeTqcHjDMM+ysThE7uUhnYcMShlBPpMqpLEZX8MxaniDfhaOJszDzUZO7dVYVdJsmaoatakl1oDW/hbdbSN3KVXrQkEaqdR5cOeh3Q44PeDRtzDXpTQjkfgY5lXF7TOq7ZrTRh+zGHmSUTDZmNGYS3mKzVXLmUHxCnrCulhxAjebPD92O8pmv6Naa2IryiCRs+XLCmgGUbswXT1VJNByhmI2oAwUWJ3UqfOmgiRtYSRGltsuYnFqmpHvoB1O6BC49prAoKr7XogGtKBT4q79N4vF9ZImA2px3qYs4fDKmgv8ALpwEcnQyVJLD6wdOPnE9lG4AXrp5w+kCWbEZyXdJcpcxLClCK0X0juWpOlGA9IVAAGTdtqxKSAZj1y1AJRaqrBifWSjDxLUCtDQwzCbMdyXxNGu3gIDLqcrCtgACQFpbfU1JmlzRUphkUXOZsuVK79B4jUmtN4YWMTy2KKVDGY4uQSK3Pw6e6OZTUVljSb6L1hyEdiB2ABZzQUuDSg/OIqtMBC5pYr6QynMP4dadYaeQJZ8/LYKzE7gPmTYawxZBJzOxrbwhvCONLAnzhyhJQppU1PEnieJiDFsxuDVeX4wxEk7GAWW/y8uMQPLEypUmu9Tr5GK2F2ZmOaYcx3MQKi9QFH4wXlSwooo84AKuD2eqACg6bv6xepCpHYAK2JwqvTMLjRgSGX7rC4/GBe0MDmXLPTvk3OBSYvMhdeqchl3wchQ02toTSawzzXavZHwmZhj3y7qUzrQ301IPC44QD+nzQUWdmmCWxZSGKTkNCpo41pXfwF49Ux0tAcyllmGl0FWO4Z10YWIvpehBgVtbZ0p/+6QIaf8AcJZf/sr6H81R/FWJvqxmsTWfz+St9Gdb8qnj8vgqbD2rnQFZve01NAk0ffUWJ9xtvgys1JniqQwsJiWYfwsCPPKw8owu2ezk/DnvZZJA0my7UGviHD3iIsJ2omI6NMWpUEM66Mp3OvEEAgg8bXjiVMkvKG0Tw5MJvxsXjI9IGLZPtRVf3iA0H30uV63FiTli7LcEAqQQbgi4PMGM9srbEuaA0twDwrbyi6q0NUPdMbkUrLbiWSuvNSDpU7oiTJpQaDEKKErG3CzBkY2BrVGO4K/HkaHkdYvwzg5FLG90gMyblAW5ZtB7/lF2Ke0sAk+W0qauZW1GhtcEEaEGOXCMsKS0JuST8ezFba7XTJtVwv1csGjT2sb+yD6I569IzuzNnTMQ7Jh0z1pmmuLLW5qx/LNGtk/4eyw9WnM0v2KAMeRcH5ARrcLhklKElqFUaKBQfrnGg76644rMpcW26Xlc8f78egLsDsrKw/1jfWTdTMbcd+UHTrrGgLb9BxMOpFWXnJ8eUC1tdL1B/VOG+Kc5Sm8yZp11xrj4xWEJ5pK/VAE1oa2pWl762IPTjpDsNKKjxMWY63NNTSg3UrTdE0qWFFFFBFTFY5UBuObE2FbC8cnZZmzAt2PlAnG7TFclbmwUV5UzEA5dRr8Ysier+l5MNP7R2Xs4Zsxp1AudN/kPdABTwKTG9IAHgN1zqfd+rAwi0AzeI8afKOooAoo/X4x2vC5/WvCADp5/rrEM0K4KMAVIoVIrUcCOHWJwvGIpkoG9+NqXp1gAGYnZhy0Wk2X+6mEkjnLm3Zd9jUaAFRGcxOxKk9wTmF2kP4Zq8xudd2YVBpYmNDM2k2bu5Ch2WxWtElmn7aZe9x4Fq2m68SYTZ9G72a5mzaUzEUVK6iUlSEHOpY72NokhZKHRFbTCxfcjAuhBIIII1BFCOojmaPQsfs2XOFJi33MLMPPf5wG/6R/zh/t/rFyPJWNmdZwpp6MZChQjFwzhsQzpAPXcd46RNHTCayNNraLOB29Ml0WcDNQaNpMXmDv+B5mNJsPtBLm1BmDMGNGpSoqcuZTo1KA8wSIx7LFaZhr1U0PEaxSt4ae4aNSj8Rkvts2v5PWTMDUzjo4NCOhGn61iVZzr6X1i+0B4h95Rr5X5b4812V2mmyfDM8S8fzEbXZe15c0Ay3APCtvKKMoyg8SRqQcLF5QYSnyjMGZHqDW4Nx05iOYDZaoL/E1N9an8BaOChNbo/tDf94aMPlupE64vL9qKfxj0PPennbS5gyJrBbA4WEZDG/4gSFnnDyJM7ETASPqwuW3pULMLDedOca9tLRh8R2QmSp0ydg5gCzTV5bUBU8Zb0Nrk5SKQnnGgWM4Zptn7YScSqkq41luMrDnwYc1JHOL/AHnGPPtpHE4d6LImkmhVpQRqsLFnbkKagb62gzgduzyUSdJAYqKkZgmegzLUgmu8ChrpBBuWmgsj47T0aVpAKZUOQbsoAp5RFNfKaImZyNaUrzJ36/GH4FmK1YAXtQk28wKGJJ2IC668ITgvLPyJN4IlwgJDzPE1uOUUrQgE2N45i8UVsBf9aVgZO2i0w0l0ahuTUKOF/W6itIJYeUxHj9HgdfLhHQAuWk2aanMgroD4mFwM1vDuNLiDOHw+W5N/hEqIAKKKCBmI2wuYypNHmf8AEGlaV32DH+VhqKQAEZ04IpZzQD9AdYp4PHtMchZZEsV8bWrwy8eYNx1qIhfAp9rPopJBYeEqTTKBUioGnhBpXjcl6Bpi0UGTKAopHhcZTQDIVoEp8tKagFibjgGyKC7VFhoL3qdLf3pF2K2FkIgogAHvJvvJudYdPxAW2rHRRSp98AE8UnnlvsyKb2INugIoff8AmOZC/wBoAeCC4HNm+FOusWUlUpy0G4dBABWElUqyqb7ySWNToC1SBUxIk6liKXAsa3PG0WSIG7RxaSqe21lQAtMa3qKPibADUgQAdfAZbyCE4oRWWePh9Q8141IaM7iNiSMWrtKAlOGysV8UpiNcpFmFbVFCCLgG0Epeznm3xFlP7ENWo4Tn9cfwL4da54Ly0AAAAAAoALAAaADcI6jOUXlM4nXGaxJHkm0djYjCPWhW9mF0bz/sYNbK7WlaJiB0O49DHoE2WGBVgGU6gioPUGMP2s7KIiNOkg5fWTULxI5comzC14lp+yuvq8dZj90fT7X/AEafC4tZi+Eh1IupoddxB1iaXmX7I1H7tyaD7jar0NRYAZdY8p2fOnSTWUxoPVOnkd0bDZHatHok4ZW9x/qIisqnW8MsVX13LMXh+jY4bGK5y3Vxco1mHPgwvqCRzi5AbMrqK0ddQR6SniCLqeYiaVNdf81PITF8tHHSht6xiNPJI4tdhKEBEGGxCuKqajQ8QeDA3B5G8TV/vDERLKAYsK1OtzQ2ArStNALw8kbyOQ/WsVMVj1QEgig1Y6D84FrimdrCq72O/X0SLH+u6AAjjZr3FKcBuPUwNw+BdzWYQ1GzKAKBd2oNxyPxgxhkYij3XgdYsIoFlH6/GACDD4RUpx3cB0EWDz0/WsNdgt/eT+J3RxHBOtTw09wPzgAdc8h8f6RVImE28Ir7qVr1Bt8eF7tYCYvHmYxl4cd5SzsbSVO8Mwu5/gXzKwAXMbtBZS1Zt9Km9TuVVF3Y8FEUss6f9oWkyvYBpOmffZfsh/CpzfxC4hqYeVI+unTMz0p3r0ooPqoBaUnIcBUnWCGGxCTFDy3V1OjKQynoRaADuHkKihEUKosFUAAdAIkhQiYAFHaxTnYsUsQANWNgIz3/AFbhf3sw8xLeh5jwQnJHSizKRyOwo3Tyw2OmOCOmADkcpDt0IQhjGlViASGQ5pbZT8D1EWxCMcygpLDJKrJxeYvAV2T2tK0TEDz3e/dGwwWNDistsw4b/wCseZ4lAd0N2FinWZlViBw84zb+P9PaZt8TlfWfhJb9nqkm32Ry8UPoHoPUPTjWhizLxCk5WGRjuOh+6dG+fECKGGbMgZrmmsXcOgYZWFRQWN4rplqccDsTg89L0I0I1FfgehtDpMjKPG1edKDzFTEGzJhPeKSSFai1uQKcTc+cLFsc9N1reUPLI/FDcdtMJQDfpQVJuBYeYh8r6zUX9oW94gTsKWGqzXJIqam9B87xo6UFrQDIcPhFTmf1uizSG7j0jLbI2vOdpgd65a08Kil+QhN4AL4mRPZ6LMCy6qQVoGAFyCCDmJYaggUJFN5cuKDGskCY2hfQUqbZqXA4DlxFbchydTDcT4VbLbwk248YUpYi2NLLwVpUvKwMyZmmkGgqQtDuC6eqL8otSySKuMutVrX+8QbKYtLBa5vc6xEHLTAGNRc8NGAGkRVeVi829ehtY0TCcXBCAovtm3uBF/h5GJZMinG+rH0m/L9aRIl2Nd2kTROcg2aHJKgELpStOjVFCK79ac70sh8g8RqevDU+I298SzjY9D8jGXb6zFdzM8UsSu8yHQta7e0L6Go5QAX12hNnH/49AmnfMKoP9MazW52XmSKRZweBWXVhVnb0pjGrtTidw/hFANwiyI7AAoUKFAAoHbbnAynTipr0pFjFuQuv6rGX7WzWUBFNFYNUcbrv13wvyOorO38GfRBQUEQz8GG1EWU0hyxrr7ls8434yyirgtqz8MbEuvDePzjYbI7RSp2/K361EZOasCtpeGjLY8RFW3jR7jo0+NzZN+M9nrLgMcxqraCYlK8gdzC+jA++JGxJApOHh/epXL/OtynxFqkiMv2SxTsgDMTYaxp5Zo1rWiimaU4Y2hk/Z4ehoHGoNeO+x0i9IwqpTju4DoIoYj6vESFl+FZhfOo9E0UtWmgNbkihO+LW0pzJLLKaG19d/OOiMnnTwtM2/QD9c4WHdmUFlycq1P8ASB6MQkyZ64dgGNyBUCl+UXcC5YVJrr+EPGsizvBNMlgimm+o3EXrA7E42XKp4qm4UAVqSanKq3djy+EEZpopI3VgB2XQNLWcwrMeoZzrQNTKPZHhFhQWhDJRhJk41nkoh/ZA+Jv9R10H8KeZOkWJjrLMtKBJdCopRVDWyLwFRWg4jjSLcJ5YPhIBBsQRUEcCDrANMdFI4RROExAFZq95SwcU8JYb2BAodaVGkPXZMpaFAy0IoqzJioP5A2WnlEz2EAHJs4L14QH21thZK5plTUgBFpW5uWJIAAFz03xzak9klsymhyk1sfnHnE7ENMnfWMWpTXpCivKWDqx/Si5PZZ2ntidiSMxAWxCgEIpViQVBPiahHiPs1AFYGfReZ95gnl8Y6fnEvdLwjTr48EujEs5lk3k//9k="));
        slideModelList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwWSIBpZqbvdFwF-8sQF9lIKtfAgibSrKeVlkdwuWisC8ftFWLUo5-wGsukZpS0gusduM&usqp=CAU"));
        slideModelList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvLtEIUfMHFV1neJFTRnuux0LfBlN3MnOqcFnn8bPCx0YPUWCKNo44DhIhYBAJipS6bss&usqp=CAU"));

        imageSlider.setImageList(slideModelList,true);



        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        navigationDrawer();


//        bestDoctorRecycler();
//        nearbyRecycler();
//        categoryRecycler();


    }



    //Normal functions

    public void callProfileScreen(View view){
        startActivity(new Intent(getApplicationContext(), UserPorfile.class));
    }





    //Navigation Drawer Function
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.teal_700));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //TRanslate view, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }

        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_cate:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;

            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(),UserPorfile.class));
                break;
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(),PatientDashboard.class));
                break;
            case R.id.nav_booking:
                startActivity(new Intent(getApplicationContext(),doctorprofile.class));
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginSelection.class));
                finish();
                break;

        }
        return true;
    }


    //Recycler Views Functions
//    private void bestDoctorRecycler() {
//
//        bestDoctorRecycler.setHasFixedSize(true);
//        bestDoctorRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        ArrayList<BestDoctorHelperClass> bestDoctorLocations = new ArrayList<>();
//        bestDoctorLocations.add(new BestDoctorHelperClass(R.drawable.doc1, "Doctor 1", "What is Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting industry Lorem Ipsum"));
//        bestDoctorLocations.add(new BestDoctorHelperClass(R.drawable.doc2, "Doctor 2", "What is Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting industry Lorem Ipsum"));
//        bestDoctorLocations.add(new BestDoctorHelperClass(R.drawable.doc3, "Doctor 3", "What is Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting industry Lorem Ipsum"));
//        bestDoctorLocations.add(new BestDoctorHelperClass(R.drawable.doc4, "Doctor 4", "What is Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting industry Lorem Ipsum"));
//
//        adapter = new BestDoctorAdapter(bestDoctorLocations);
//        bestDoctorRecycler.setAdapter(adapter);
//    }
//
//    private void nearbyRecycler() {
//        nearbyRecycler.setHasFixedSize(true);
//        nearbyRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        ArrayList<NearbyHelperClass> nearbyDoctors = new ArrayList<>();
//        nearbyDoctors.add(new NearbyHelperClass(R.drawable.doc4, "Doctor 232", "What is Lorem Ipsum Lorem Ipsum is simply dummy text of the printing"));
//        nearbyDoctors.add(new NearbyHelperClass(R.drawable.doc2, "Doctor 75", "What is Lorem Ipsum Lorem Ipsum is simply dummy text of the printing"));
//        nearbyDoctors.add(new NearbyHelperClass(R.drawable.doc1, "Doctor 212", "What is Lorem Ipsum Lorem Ipsum is simply dummy text of the printing"));
//
//        adapter = new NearbyAdapter(nearbyDoctors);
//        nearbyRecycler.setAdapter(adapter);
//    }
//
//    private void categoryRecycler() {
//        categoryRecycler.setHasFixedSize(true);
//        categoryRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        ArrayList<CategoryHelperClass> doctorsCatetory = new ArrayList<>();
//        doctorsCatetory.add(new CategoryHelperClass(R.drawable.brain_icon, "Neurologist"));
//        doctorsCatetory.add(new CategoryHelperClass(R.drawable.heart_icon, "Cardiologist"));
//        doctorsCatetory.add(new CategoryHelperClass(R.drawable.dentist_icon, "Dentist"));
//        doctorsCatetory.add(new CategoryHelperClass(R.drawable.eye_icon, "Eye"));
//
//        adapter = new CategoryAdapter(doctorsCatetory);
//        categoryRecycler.setAdapter(adapter);
//
//
//    }


}

