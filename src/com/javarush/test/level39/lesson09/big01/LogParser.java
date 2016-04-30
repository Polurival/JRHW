package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;

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

public class LogParser implements IPQuery
{

    private Path logDir;

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        return getUniqueIPsSet(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        return getUniqueIPsSet(after, before);
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
                addIP(after, before, IPsForUser, parts);
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
                addIP(after, before, IPsForEvent, parts);
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
                addIP(after, before, IPsForEvent, parts);
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

    private Set<String> getUniqueIPsSet(Date after, Date before)
    {
        Set<String> uniqueIPs = new HashSet<>();

        for (String line : getLinesList())
        {
            String[] parts = line.split("\\t");

            addIP(after, before, uniqueIPs, parts);
        }
        return uniqueIPs;
    }

    private void addIP(Date after, Date before, Set<String> IPs, String[] parts)
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
            IPs.add(parts[0]);
        } else if (after == null)
        {
            if (partDate <= before.getTime())
            {
                IPs.add(parts[0]);
            }
        } else if (before == null)
        {
            if (partDate >= after.getTime())
            {
                IPs.add(parts[0]);
            }
        } else {
            if (partDate >= after.getTime() && partDate <= before.getTime())
            {
                IPs.add(parts[0]);
            }
        }
    }
}
