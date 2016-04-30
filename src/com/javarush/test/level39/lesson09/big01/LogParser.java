package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;
import com.javarush.test.level39.lesson09.big01.query.UserQuery;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery
{

    private Path logDir;

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        Set<String> uniqueIPs = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            addEntity(after, before, uniqueIPs, parts, 0);
        }
        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        Set<String> IPsForUser = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (user.equals(parts[1]))
            {
                addEntity(after, before, IPsForUser, parts, 0);
            }
        }
        return IPsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (event.toString().equals(parts[3].split(" ")[0]))
            {
                addEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (status.toString().equals(parts[4]))
            {
                addEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    private List<String> getLinesList()
    {
        String[] files = logDir.toFile().list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".log");
            }
        });

        List<String> lines = new ArrayList<>();
        for (String file : files)
        {
            try
            {
                lines.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return lines;
    }

    private void addEntity(Date after, Date before, Set<String> enteties, String[] parts, int part)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        long partDate = 0;
        try
        {
            partDate = dateFormat.parse(parts[2]).getTime();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        if (after == null && before == null)
        {
            enteties.add(parts[part]);
        } else if (after == null)
        {
            if (partDate <= before.getTime())
            {
                enteties.add(parts[part]);
            }
        } else if (before == null)
        {
            if (partDate >= after.getTime())
            {
                enteties.add(parts[part]);
            }
        } else
        {
            if (partDate >= after.getTime() && partDate <= before.getTime())
            {
                enteties.add(parts[part]);
            }
        }
    }


    @Override
    public Set<String> getAllUsers()
    {
        Set<String> allUsers = new HashSet<>();

        for (String line : getLinesList())
        {
            allUsers.add(line.split("\\t")[1]);
        }
        return allUsers;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");
            addEntity(after, before, users, parts, 1);
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        Set<String> userEvents = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (user.equals(parts[1]))
            {
                addEntity(after, before, userEvents, parts, 2);
            }
        }
        return userEvents.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        Set<String> usersForIP = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (ip.equals(parts[0]))
            {
                addEntity(after, before, usersForIP, parts, 1);
            }
        }
        return usersForIP;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        Set<String> loggedUsers = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (Event.LOGIN.toString().equals(parts[3]))
            {
                addEntity(after, before, loggedUsers, parts, 1);
            }
        }
        return loggedUsers;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        Set<String> downloadedPluginUsers = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (Event.DOWNLOAD_PLUGIN.toString().equals(parts[3]))
            {
                addEntity(after, before, downloadedPluginUsers, parts, 1);
            }
        }
        return downloadedPluginUsers;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        Set<String> wroteMessageUsers = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (Event.WRITE_MESSAGE.toString().equals(parts[3]))
            {
                addEntity(after, before, wroteMessageUsers, parts, 1);
            }
        }
        return wroteMessageUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        Set<String> solvedTaskUsers = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0]))
            {
                addEntity(after, before, solvedTaskUsers, parts, 1);
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        Set<String> solvedTaskUsers = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0])
                    && task == Integer.valueOf(parts[3].split(" ")[1]))
            {
                addEntity(after, before, solvedTaskUsers, parts, 1);
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        Set<String> doneTaskUsers = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0]))
            {
                addEntity(after, before, doneTaskUsers, parts, 1);
            }
        }
        return doneTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        Set<String> doneTaskUsers = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0])
                    && task == Integer.valueOf(parts[3].split(" ")[1]))
            {
                addEntity(after, before, doneTaskUsers, parts, 1);
            }
        }
        return doneTaskUsers;
    }
}
